package com.example.bff.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Rate Limiting 필터
 * - Redis 기반 Token Bucket 알고리즘
 * - 사용자별 요청 수 제한
 */
@Component
@Order(2)
public class RateLimitingFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(RateLimitingFilter.class);

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Value("${rate-limit.enabled:true}")
    private boolean rateLimitEnabled;

    @Value("${rate-limit.requests-per-second:100}")
    private long requestsPerSecond;

    @Autowired
    public RateLimitingFilter(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Rate limiting 비활성화 시 스킵
        if (!rateLimitEnabled) {
            return chain.filter(exchange);
        }

        // 공개 엔드포인트는 rate limiting 제외
        String path = exchange.getRequest().getPath().value();
        if (isPublicEndpoint(path)) {
            return chain.filter(exchange);
        }

        // userId 추출 (JwtAuthenticationFilter에서 설정)
        String userId = (String) exchange.getAttribute("userId");
        if (userId == null) {
            // 인증되지 않은 요청 (JWT 검증 실패)
            return chain.filter(exchange);
        }

        String rateLimitKey = "rate_limit:" + userId;

        // Redis에서 현재 요청 수 확인
        return redisTemplate.opsForValue().increment(rateLimitKey, 1)
            .flatMap(count -> {
                // 제한 초과
                if (count > requestsPerSecond) {
                    log.warn("Rate limit exceeded for userId: {} (count: {})", userId, count);
                    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                    return exchange.getResponse().setComplete();
                }

                // 첫 요청일 때 TTL 설정 (1초)
                if (count == 1) {
                    return redisTemplate.expire(rateLimitKey, Duration.ofSeconds(1))
                        .then(chain.filter(exchange));
                }

                return chain.filter(exchange);
            })
            .onErrorResume(ex -> {
                // Redis 오류 시 요청 허용 (fail open)
                log.error("Rate limiting error: {}", ex.getMessage());
                return chain.filter(exchange);
            });
    }

    /**
     * 공개 엔드포인트 확인
     */
    private boolean isPublicEndpoint(String path) {
        return path.equals("/health") ||
               path.equals("/ready") ||
               path.equals("/login") ||
               path.equals("/refresh") ||
               path.startsWith("/docs") ||
               path.startsWith("/swagger") ||
               path.startsWith("/actuator");
    }
}
