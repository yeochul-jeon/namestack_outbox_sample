package com.example.bff.handler;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.reactor.retry.RetryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 프록시 핸들러
 * - 여러 마이크로서비스로 HTTP 요청 전달
 * - K8s Service Discovery를 통해 서비스 호출
 */
@Component
public class ProxyHandler {

    private static final Logger log = LoggerFactory.getLogger(ProxyHandler.class);
    private final WebClient.Builder webClientBuilder;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    @Value("${services.user-service:http://user-service:8080}")
    private String userServiceUrl;

    @Value("${services.order-service:http://order-service:8080}")
    private String orderServiceUrl;

    @Value("${services.payment-service:http://payment-service:8080}")
    private String paymentServiceUrl;

    @Autowired
    public ProxyHandler(WebClient.Builder webClientBuilder,
                       CircuitBreakerRegistry circuitBreakerRegistry,
                       RetryRegistry retryRegistry) {
        this.webClientBuilder = webClientBuilder;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.retryRegistry = retryRegistry;
    }

    /**
     * User Service로 프록시
     * GET/POST /api/users/** -> user-service/users/**
     */
    public Mono<ServerResponse> proxyToUserService(ServerRequest request) {
        return proxyRequest(request, userServiceUrl, "/api/users", "", "user-service");
    }

    /**
     * Order Service로 프록시
     * GET/POST /api/orders/** -> order-service/orders/**
     */
    public Mono<ServerResponse> proxyToOrderService(ServerRequest request) {
        return proxyRequest(request, orderServiceUrl, "/api/orders", "", "order-service");
    }

    /**
     * Payment Service로 프록시
     * GET/POST /api/payments/** -> payment-service/payments/**
     */
    public Mono<ServerResponse> proxyToPaymentService(ServerRequest request) {
        return proxyRequest(request, paymentServiceUrl, "/api/payments", "", "payment-service");
    }

    /**
     * 일반 프록시 요청 처리
     *
     * @param request       클라이언트 요청
     * @param serviceUrl    백엔드 서비스 URL (K8s Service 이름)
     * @param prefixPath    제거할 경로 접두사 (예: /api/users)
     * @param newPrefix     새 경로 접두사 (예: "")
     * @param serviceName   Circuit Breaker 이름 (예: "user-service")
     */
    private Mono<ServerResponse> proxyRequest(ServerRequest request, String serviceUrl,
                                              String prefixPath, String newPrefix, String serviceName) {
        // 경로 변환: /api/users/123 -> /users/123
        String originalPath = request.path();
        String newPath = originalPath.replaceFirst(prefixPath, newPrefix);

        log.info("Proxying {} {} to {} {} (service: {})", request.method(), originalPath, serviceUrl, newPath, serviceName);

        WebClient webClient = webClientBuilder.baseUrl(serviceUrl).build();

        // Circuit Breaker와 Retry 적용
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(serviceName);
        Retry retry = retryRegistry.retry(serviceName);

        return webClient.method(request.method())
            .uri(newPath)
            // 클라이언트의 헤더를 포함 (Authorization 등)
            .headers(headers -> headers.addAll(request.headers().asHttpHeaders()))
            // 요청 바디 포함
            .bodyValue(request.bodyToMono(String.class).defaultIfEmpty(""))
            .retrieve()
            .onStatus(
                status -> !status.is2xxSuccessful(),
                response -> response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(
                        new RuntimeException("Backend service error: " + response.statusCode() + " " + body)
                    ))
            )
            // 응답을 바이너리로 받아서 그대로 전달
            .bodyToMono(String.class)
            // Resilience4j Operator 적용 (Retry -> CircuitBreaker 순서)
            .transformDeferred(RetryOperator.of(retry))
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
            .flatMap(body -> {
                if (body == null || body.isEmpty()) {
                    return ServerResponse.ok().build();
                }
                return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body);
            })
            .onErrorResume(ex -> {
                log.error("Proxy error for service {}: {}", serviceName, ex.getMessage(), ex);
                return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("{\"error\": \"Service unavailable\", \"message\": \"" + ex.getMessage() + "\"}");
            });
    }
}
