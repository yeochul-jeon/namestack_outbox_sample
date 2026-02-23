package com.example.bff.config;

import com.example.bff.handler.AuthHandler;
import com.example.bff.handler.CompositionHandler;
import com.example.bff.handler.ProxyHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 라우팅 설정 (RouterFunction 기반)<br/><br/>
 *
 * - 함수형 엔드포인트를 사용한 라우팅<br/>
 * - 각 마이크로서비스로의 경로 매핑<br/>
 * - 인증 관련 엔드포인트<br/>
 * - 외부 설정(application.yml)에서 엔드포인트를 동적으로 관리
 *
 * @see RouteProperties
 */
@Slf4j
@Configuration
public class RouteConfiguration {

    private final RouteProperties routeProperties;

    public RouteConfiguration(RouteProperties routeProperties) {
        this.routeProperties = routeProperties;
    }

    /**
     * 라우팅 규칙 정의 (외부 설정 기반)
     */
    @Bean
    public RouterFunction<ServerResponse> routes(ProxyHandler proxyHandler,
                                                  AuthHandler authHandler,
                                                  CompositionHandler compositionHandler) {
        var routerBuilder = RouterFunctions.route();

        // 1. 공개 엔드포인트 등록
        if (routeProperties.getPublicEndpoints() != null) {
            for (RouteProperties.RouteEndpoint endpoint : routeProperties.getPublicEndpoints()) {
                routerBuilder = registerEndpoint(routerBuilder, endpoint, authHandler, compositionHandler, proxyHandler);
            }
        }

        // 2. 보호된 엔드포인트 등록
        if (routeProperties.getProtectedEndpoints() != null) {
            for (RouteProperties.RouteEndpoint endpoint : routeProperties.getProtectedEndpoints()) {
                routerBuilder = registerEndpoint(routerBuilder, endpoint, authHandler, compositionHandler, proxyHandler);
            }
        }

        // 3. 프록시 라우트 등록
        if (routeProperties.getProxyRoutes() != null) {
            for (RouteProperties.ProxyRoute proxyRoute : routeProperties.getProxyRoutes()) {
                routerBuilder = registerProxyRoute(routerBuilder, proxyRoute, proxyHandler);
            }
        }

        // 4. 기본 헬스 체크 엔드포인트 (설정에 없는 경우)
        routerBuilder = routerBuilder
            .GET("/health", request -> {
                try {
                    return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .syncBody("{\"status\": \"UP\", \"service\": \"bff-service\"}");
                } catch (Exception e) {
                    return ServerResponse.status(500).syncBody("Internal Server Error");
                }
            })
            .GET("/ready", request -> {
                try {
                    return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .syncBody("{\"ready\": true}");
                } catch (Exception e) {
                    return ServerResponse.status(500).syncBody("Internal Server Error");
                }
            });

        return routerBuilder.build();
    }

    /**
     * 일반 엔드포인트 등록
     */
    private RouterFunctions.Builder registerEndpoint(
            RouterFunctions.Builder routerBuilder,
            RouteProperties.RouteEndpoint endpoint,
            AuthHandler authHandler,
            CompositionHandler compositionHandler,
            ProxyHandler proxyHandler) {

        try {
            Object handler = getHandler(endpoint.getHandler(), authHandler, compositionHandler, proxyHandler);
            if (handler == null) {
                log.warn("Handler not found for endpoint: {} - {}", endpoint.getPath(), endpoint.getHandler());
                //TODO 예외 처리 로직 추가
                return routerBuilder;
            }

            Method handlerMethod = handler.getClass()
                .getMethod(endpoint.getHandlerMethod(), ServerRequest.class);
            String httpMethod = endpoint.getMethod().toUpperCase();

            log.info("Registering {} endpoint: {} -> {}.{}", httpMethod, endpoint.getPath(),
                endpoint.getHandler(), endpoint.getHandlerMethod());

            return switch (httpMethod) {
                case "GET" -> routerBuilder.GET(endpoint.getPath(),
                    request -> invokeHandler(handler, handlerMethod, request));
                case "POST" -> routerBuilder.POST(endpoint.getPath(),
                    request -> invokeHandler(handler, handlerMethod, request));
                case "PUT" -> routerBuilder.PUT(endpoint.getPath(),
                    request -> invokeHandler(handler, handlerMethod, request));
                case "DELETE" -> routerBuilder.DELETE(endpoint.getPath(),
                    request -> invokeHandler(handler, handlerMethod, request));
                case "PATCH" -> routerBuilder.PATCH(endpoint.getPath(),
                    request -> invokeHandler(handler, handlerMethod, request));
                default -> {
                    log.warn("Unsupported HTTP method: {}", httpMethod);
                    yield routerBuilder;
                }
            };
        } catch (NoSuchMethodException e) {
            log.error("Handler method not found for endpoint: {}", endpoint.getPath(), e);
            return routerBuilder;
        } catch (Exception e) {
            log.error("Error registering endpoint: {}", endpoint.getPath(), e);
            return routerBuilder;
        }
    }

    /**
     * 프록시 라우트 등록
     */
    private RouterFunctions.Builder registerProxyRoute(
            RouterFunctions.Builder routerBuilder,
            RouteProperties.ProxyRoute proxyRoute,
            ProxyHandler proxyHandler) {

        try {
            Method handlerMethod = proxyHandler.getClass().getMethod(proxyRoute.getHandlerMethod(), ServerRequest.class);

            for (String method : proxyRoute.getMethods()) {
                String httpMethod = method.toUpperCase();
                log.info("Registering {} proxy route: {} -> {}.{}", httpMethod, proxyRoute.getPath(), "proxyHandler", proxyRoute.getHandlerMethod());

                switch (httpMethod) {
                    case "GET":
                        routerBuilder = routerBuilder.GET(proxyRoute.getPath(), request -> invokeHandler(proxyHandler, handlerMethod, request));
                        break;
                    case "POST":
                        routerBuilder = routerBuilder.POST(proxyRoute.getPath(), request -> invokeHandler(proxyHandler, handlerMethod, request));
                        break;
                    case "PUT":
                        routerBuilder = routerBuilder.PUT(proxyRoute.getPath(), request -> invokeHandler(proxyHandler, handlerMethod, request));
                        break;
                    case "DELETE":
                        routerBuilder = routerBuilder.DELETE(proxyRoute.getPath(), request -> invokeHandler(proxyHandler, handlerMethod, request));
                        break;
                    case "PATCH":
                        routerBuilder = routerBuilder.PATCH(proxyRoute.getPath(), request -> invokeHandler(proxyHandler, handlerMethod, request));
                        break;
                    default:
                        log.warn("Unsupported HTTP method: {}", httpMethod);
                }
            }
            return routerBuilder;
        } catch (NoSuchMethodException e) {
            log.error("Handler method not found for proxy route: {}", proxyRoute.getPath(), e);
            return routerBuilder;
        } catch (Exception e) {
            log.error("Error registering proxy route: {}", proxyRoute.getPath(), e);
            return routerBuilder;
        }
    }

    /**
     * 핸들러 객체 반환
     */
    private Object getHandler(String handlerName, AuthHandler authHandler, CompositionHandler compositionHandler, ProxyHandler proxyHandler) {
        return switch (handlerName.toLowerCase()) {
            case "authhandler" -> authHandler;
            case "compositionhandler" -> compositionHandler;
            case "proxyhandler" -> proxyHandler;
            default -> null;
        };
    }

    /**
     * 핸들러 메소드 호출 (Mono<ServerResponse> 반환)
     */
    @SuppressWarnings("unchecked")
    private Mono<ServerResponse> invokeHandler(Object handler, Method method, ServerRequest request) {
        try {
            Object result = method.invoke(handler, request);

            // Mono<ServerResponse>를 반환
            if (result instanceof Mono) {
                return (Mono<ServerResponse>) result;
            }

            // ServerResponse를 직접 반환하면 Mono로 래핑
            if (result instanceof ServerResponse) {
                return Mono.just((ServerResponse) result);
            }

            log.error("Handler method returned unexpected type: {}", result.getClass().getName());
            return Mono.error(new IllegalStateException("Handler method must return ServerResponse or Mono<ServerResponse>"));
        } catch (Exception e) {
            log.error("Error invoking handler method", e);
            return Mono.error(e);
        }
    }
}
