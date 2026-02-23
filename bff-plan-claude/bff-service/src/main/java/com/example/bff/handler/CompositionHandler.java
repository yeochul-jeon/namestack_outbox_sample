package com.example.bff.handler;

import com.example.bff.dto.DashboardResponse;
import com.example.bff.dto.Order;
import com.example.bff.dto.Payment;
import com.example.bff.dto.User;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * API Composition Handler
 * - 여러 마이크로서비스를 병렬로 호출하여 데이터 조합
 * - Mono.zip을 사용한 동시성 처리
 * - 부분 실패 시 우아한 처리
 */
@Component
public class CompositionHandler {

    private static final Logger log = LoggerFactory.getLogger(CompositionHandler.class);

    private final WebClient.Builder webClientBuilder;
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;

    @Value("${services.user-service:http://user-service:8080}")
    private String userServiceUrl;

    @Value("${services.order-service:http://order-service:8080}")
    private String orderServiceUrl;

    @Value("${services.payment-service:http://payment-service:8080}")
    private String paymentServiceUrl;

//    @Autowired
    public CompositionHandler(WebClient.Builder webClientBuilder,
                              CircuitBreakerRegistry circuitBreakerRegistry,
                              RetryRegistry retryRegistry) {
        this.webClientBuilder = webClientBuilder;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.retryRegistry = retryRegistry;
    }

    /**
     * Dashboard 데이터 조회
     * - User, Order, Payment 정보를 병렬로 호출하여 조합
     *
     * @param request ServerRequest
     * @return Dashboard 응답
     */
    public Mono<ServerResponse> getDashboard(ServerRequest request) {
        String userId = request.pathVariable("userId");

        log.info("Fetching dashboard for userId: {}", userId);

        // Circuit Breaker와 Retry 레지스트리에서 인스턴스 가져오기
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("dashboard");
        Retry retry = retryRegistry.retry("dashboard");

        // 병렬 호출
        Mono<User> userMono = getUserInfo(userId)
            .timeout(Duration.ofSeconds(3))
            .onErrorResume(ex -> {
                log.warn("Failed to fetch user info for userId: {}, error: {}", userId, ex.getMessage());
                return Mono.just(new User());  // 빈 User 객체 반환
            });

        Mono<List<Order>> ordersMono = getUserOrders(userId)
            .timeout(Duration.ofSeconds(3))
            .onErrorResume(ex -> {
                log.warn("Failed to fetch orders for userId: {}, error: {}", userId, ex.getMessage());
                return Mono.just(new ArrayList<>());  // 빈 리스트 반환
            });

        Mono<List<Payment>> paymentsMono = getUserPayments(userId)
            .timeout(Duration.ofSeconds(3))
            .onErrorResume(ex -> {
                log.warn("Failed to fetch payments for userId: {}, error: {}", userId, ex.getMessage());
                return Mono.just(new ArrayList<>());  // 빈 리스트 반환
            });

        // 모든 호출을 병렬로 실행하고 결과 조합
        return Mono.zip(userMono, ordersMono, paymentsMono)
            .map(tuple -> new DashboardResponse(
                tuple.getT1(),  // User
                tuple.getT2(),  // Orders
                tuple.getT3()   // Payments
            ))
            .transformDeferred(RetryOperator.of(retry))
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
            .flatMap(dashboard -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dashboard))
            .onErrorResume(ex -> {
                log.error("Dashboard composition failed for userId: {}", userId, ex);
                return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue("{\"error\": \"Dashboard service temporarily unavailable\"}");
            });
    }

    /**
     * User 서비스에서 사용자 정보 조회
     *
     * @param userId 사용자 ID
     * @return User 정보
     */
    private Mono<User> getUserInfo(String userId) {
        log.debug("Calling User Service for userId: {}", userId);

        WebClient webClient = webClientBuilder.baseUrl(userServiceUrl).build();

        return webClient.get()
            .uri("/users/{userId}", userId)
            .retrieve()
            .bodyToMono(User.class)
            .doOnSuccess(user -> log.debug("User info retrieved: {}", user))
            .doOnError(ex -> log.error("Error fetching user info: {}", ex.getMessage()));
    }

    /**
     * Order 서비스에서 사용자 주문 조회
     *
     * @param userId 사용자 ID
     * @return Order 리스트
     */
    private Mono<List<Order>> getUserOrders(String userId) {
        log.debug("Calling Order Service for userId: {}", userId);

        WebClient webClient = webClientBuilder.baseUrl(orderServiceUrl).build();

        return webClient.get()
            .uri("/orders?userId={userId}", userId)
            .retrieve()
            .bodyToFlux(Order.class)
            .collectList()
            .doOnSuccess(orders -> log.debug("Orders retrieved: {} items", orders.size()))
            .doOnError(ex -> log.error("Error fetching orders: {}", ex.getMessage()));
    }

    /**
     * Payment 서비스에서 사용자 결제 조회
     *
     * @param userId 사용자 ID
     * @return Payment 리스트
     */
    private Mono<List<Payment>> getUserPayments(String userId) {
        log.debug("Calling Payment Service for userId: {}", userId);

        WebClient webClient = webClientBuilder.baseUrl(paymentServiceUrl).build();

        return webClient.get()
            .uri("/payments?userId={userId}", userId)
            .retrieve()
            .bodyToFlux(Payment.class)
            .collectList()
            .doOnSuccess(payments -> log.debug("Payments retrieved: {} items", payments.size()))
            .doOnError(ex -> log.error("Error fetching payments: {}", ex.getMessage()));
    }
}
