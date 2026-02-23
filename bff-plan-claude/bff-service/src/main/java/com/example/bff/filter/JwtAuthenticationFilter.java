package com.example.bff.filter;

import com.example.bff.service.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * JWT 인증 필터
 * - 요청에서 JWT 토큰 추출
 * - 토큰 검증
 * - 사용자 정보를 exchange attributes에 저장
 */
@Component
public class JwtAuthenticationFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 로그인, 헬스 체크 등 공개 엔드포인트는 필터 제외
        if (isPublicEndpoint(request.getPath().value())) {
            return chain.filter(exchange);
        }

        // 1. Authorization 헤더 또는 쿠키에서 토큰 추출
        String token = extractToken(request);

        if (token == null) {
            log.warn("No token found in request: {}", request.getPath());
            return handleAuthenticationError(exchange, "No authentication token found");
        }

        // 2. JWT 검증
        if (!jwtTokenProvider.validateToken(token)) {
            log.warn("Invalid or expired token");
            return handleAuthenticationError(exchange, "Invalid or expired token");
        }

        // 3. 토큰에서 사용자 정보 추출
        String userId = jwtTokenProvider.getUserIdFromToken(token);
        Claims claims = jwtTokenProvider.getClaimsFromToken(token);

        if (userId == null) {
            log.warn("Cannot extract userId from token");
            return handleAuthenticationError(exchange, "Cannot extract user information from token");
        }

        // 4. exchange attributes에 사용자 정보 저장
        exchange.getAttributes().put("userId", userId);
        if (claims != null && claims.containsKey("username")) {
            exchange.getAttributes().put("username", claims.get("username"));
        }
        if (claims != null && claims.containsKey("roles")) {
            exchange.getAttributes().put("roles", claims.get("roles"));
        }

        // 5. 다운스트림 서비스를 위한 헤더 추가
        ServerHttpRequest mutatedRequest = request.mutate()
            .header("X-User-Id", userId)
            .build();

        if (claims != null && claims.containsKey("roles")) {
            @SuppressWarnings("unchecked")
            List<String> roles = (List<String>) claims.get("roles");
            if (roles != null && !roles.isEmpty()) {
                mutatedRequest = mutatedRequest.mutate()
                    .header("X-User-Roles", String.join(",", roles))
                    .build();
            }
        }

        log.debug("JWT authentication successful for userId: {}", userId);
        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    /**
     * Authorization 헤더 또는 쿠키에서 토큰 추출
     */
    private String extractToken(ServerHttpRequest request) {
        // 1. Authorization 헤더에서 Bearer 토큰 추출
        String authorizationHeader = request.getHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        // 2. 쿠키에서 accessToken 추출
        if (request.getCookies().containsKey("accessToken")) {
            var cookie = request.getCookies().getFirst("accessToken");
            if (cookie != null && !cookie.getValue().isEmpty()) {
                return cookie.getValue();
            }
        }

        return null;
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
               path.startsWith("/swagger");
    }

    /**
     * 인증 오류 응답
     */
    private Mono<Void> handleAuthenticationError(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
