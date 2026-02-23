package com.example.bff.dto;

import java.util.List;

/**
 * 로그인 응답 DTO
 */
public class LoginResponse {
    private String userId;
    private String username;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
    private int expiresIn;  // 액세스 토큰 만료 시간 (초)

    public LoginResponse() {
    }

    public LoginResponse(String userId, String username, List<String> roles,
                        String accessToken, String refreshToken, int expiresIn) {
        this.userId = userId;
        this.username = username;
        this.roles = roles;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
