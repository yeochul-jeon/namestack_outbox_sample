package com.example.bff.handler;

import com.example.bff.dto.LoginRequest;
import com.example.bff.dto.LoginResponse;
import com.example.bff.service.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

/**
 * 인증 핸들러
 * - 로그인 엔드포인트
 * - 토큰 갱신 엔드포인트
 * - 로그아웃 엔드포인트
 */
@Component
public class AuthHandler {

    private static final Logger log = LoggerFactory.getLogger(AuthHandler.class);

    private final JwtTokenProvider jwtTokenProvider;
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Autowired
    public AuthHandler(JwtTokenProvider jwtTokenProvider,
                       ReactiveRedisTemplate<String, String> redisTemplate) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 로그인 엔드포인트
     */
    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequest.class)
            .flatMap(this::authenticateUser)
            .flatMap(this::generateTokensAndRespond)
            .onErrorResume(ex -> {
                log.error("Login error: {}", ex.getMessage());
                return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(Map.of("error", "Authentication failed", "message", ex.getMessage()));
            });
    }

    /**
     * 토큰 갱신 엔드포인트
     */
    public Mono<ServerResponse> refresh(ServerRequest request) {
        return request.bodyToMono(Map.class)
            .flatMap(body -> {
                String refreshToken = (String) body.get("refreshToken");
                if (refreshToken == null || refreshToken.isEmpty()) {
                    return Mono.error(new IllegalArgumentException("Refresh token is required"));
                }
                return refreshTokens(refreshToken);
            })
            .flatMap(this::generateTokensAndRespond)
            .onErrorResume(ex -> {
                log.warn("Token refresh error: {}", ex.getMessage());
                return ServerResponse.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(Map.of("error", "Token refresh failed", "message", ex.getMessage()));
            });
    }

    /**
     * 사용자 인증 (간단한 예제)
     */
    private Mono<AuthUser> authenticateUser(LoginRequest loginRequest) {
        // 실제 운영에서는 User Service를 호출하여 사용자를 검증합니다
        // 여기서는 간단한 예제로 고정된 자격증명을 사용합니다

        if ("admin".equals(loginRequest.getUsername()) &&
            "password123".equals(loginRequest.getPassword())) {
            return Mono.just(new AuthUser("user-001", "admin", Arrays.asList("ADMIN", "USER")));
        }

        if ("user".equals(loginRequest.getUsername()) &&
            "password123".equals(loginRequest.getPassword())) {
            return Mono.just(new AuthUser("user-002", "user", Arrays.asList("USER")));
        }

        return Mono.error(new IllegalArgumentException("Invalid credentials"));
    }

    /**
     * 토큰 갱신
     */
    private Mono<AuthUser> refreshTokens(String refreshToken) {
        // 토큰 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            return Mono.error(new IllegalArgumentException("Invalid or expired refresh token"));
        }

        String userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
        if (userId == null) {
            return Mono.error(new IllegalArgumentException("Cannot extract user information from token"));
        }

        // Redis에서 저장된 refresh token 확인
        return redisTemplate.opsForValue()
            .get("refresh_token:" + userId)
            .flatMap(storedToken -> {
                if (!refreshToken.equals(storedToken)) {
                    return Mono.error(new IllegalArgumentException("Refresh token mismatch"));
                }

                // 사용자 정보는 토큰에 포함되지 않으므로 기본값 사용
                // 실제로는 User Service에서 조회해야 함
                return Mono.just(new AuthUser(userId, userId, Arrays.asList("USER")));
            })
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Refresh token not found")));
    }

    /**
     * 토큰 생성 및 응답 반환
     */
    private Mono<ServerResponse> generateTokensAndRespond(AuthUser user) {
        // 액세스 토큰 생성 (1시간)
        String accessToken = jwtTokenProvider.createAccessToken(
            user.userId, user.username, user.roles
        );

        // 리프레시 토큰 생성 (7일)
        String refreshToken = jwtTokenProvider.createRefreshToken(user.userId);

        // Redis에 리프레시 토큰 저장 (7일)
        return redisTemplate.opsForValue()
            .set("refresh_token:" + user.userId, refreshToken, Duration.ofDays(7))
            .then(ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                // AccessToken을 HttpOnly 쿠키에 저장
                .cookie(ResponseCookie.from("accessToken", accessToken)
                    .httpOnly(true)
                    .secure(true)  // HTTPS에서만 전송
                    .path("/")
                    .maxAge(Duration.ofHours(1))
                    .sameSite("Strict")  // CSRF 방지
                    .build())
                // RefreshToken은 응답 본문에 포함 (클라이언트가 안전한 저장소에 보관)
                .bodyValue(new LoginResponse(
                    user.userId,
                    user.username,
                    user.roles,
                    accessToken,
                    refreshToken,
                    3600  // 액세스 토큰 만료 시간 (초)
                )));
    }

    /**
     * 로그아웃 엔드포인트
     */
    public Mono<ServerResponse> logout(ServerRequest request) {
        String userId = (String) request.exchange()
            .getAttribute("userId");

        if (userId == null) {
            return ServerResponse.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("error", "User not authenticated"));
        }

        // Redis에서 리프레시 토큰 삭제
        return redisTemplate.delete("refresh_token:" + userId)
            .then(ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                    "message", "Logout successful",
                    "userId", userId
                )));
    }

    /**
     * 내부 클래스: 인증된 사용자 정보
     */
    private static class AuthUser {
        String userId;
        String username;
        java.util.List<String> roles;

        AuthUser(String userId, String username, java.util.List<String> roles) {
            this.userId = userId;
            this.username = username;
            this.roles = roles;
        }
    }
}
