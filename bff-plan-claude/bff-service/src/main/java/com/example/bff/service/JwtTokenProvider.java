package com.example.bff.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JWT 토큰 생성 및 검증
 */
@Component
public class JwtTokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.secret:mySecretKeyForJWTTokenGenerationAndValidation123456}")
    private String secretKey;

    @Value("${jwt.access-token.validity:3600000}")  // 1시간 (밀리초)
    private long accessTokenValidity;

    @Value("${jwt.refresh-token.validity:604800000}")  // 7일 (밀리초)
    private long refreshTokenValidity;

    /**
     * 액세스 토큰 생성
     */
    public String createAccessToken(String userId, String username, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", roles);

        return createToken(claims, userId, accessTokenValidity);
    }

    /**
     * 리프레시 토큰 생성
     */
    public String createRefreshToken(String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");

        return createToken(claims, userId, refreshTokenValidity);
    }

    /**
     * 토큰 생성
     */
    private String createToken(Map<String, Object> claims, String subject, long validity) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validity);

        try {
            return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        } catch (Exception ex) {
            log.error("Error creating JWT token", ex);
            throw new RuntimeException("Failed to create JWT token", ex);
        }
    }

    /**
     * 토큰 검증
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.warn("Expired JWT token: {}", ex.getMessage());
            return false;
        } catch (JwtException | IllegalArgumentException ex) {
            log.warn("Invalid JWT token: {}", ex.getMessage());
            return false;
        }
    }

    /**
     * 토큰에서 사용자 ID(subject) 추출
     */
    public String getUserIdFromToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("Error extracting userId from token", ex);
            return null;
        }
    }

    /**
     * 토큰에서 Claims 추출
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("Error extracting claims from token", ex);
            return null;
        }
    }

    /**
     * 토큰의 만료 여부 확인
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return true;
            }
            return claims.getExpiration().before(new Date());
        } catch (Exception ex) {
            return true;
        }
    }

    /**
     * 서명 키 생성
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes();
        // HS256은 최소 256비트 (32바이트) 필요
        if (keyBytes.length < 32) {
            log.warn("JWT secret key is too short. Minimum 32 bytes required for HS256.");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
