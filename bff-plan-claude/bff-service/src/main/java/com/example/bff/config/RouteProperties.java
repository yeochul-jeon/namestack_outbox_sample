package com.example.bff.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 라우트 설정을 외부 파일(application.yml)에서 관리하기 위한 프로퍼티 클래스
 */
@Data
@Component
@ConfigurationProperties(prefix = "routes")
public class RouteProperties {

    /**
     * 공개 엔드포인트 (인증 불필요)
     */
    private List<RouteEndpoint> publicEndpoints;

    /**
     * 보호된 엔드포인트 (인증 필요)
     */
    private List<RouteEndpoint> protectedEndpoints;

    /**
     * 프록시 엔드포인트 (마이크로서비스)
     */
    private List<ProxyRoute> proxyRoutes;

    /**
     * 엔드포인트 설정
     */
    @Data
    public static class RouteEndpoint {
        /**
         * 경로 (예: /login, /api/dashboard/{userId})
         */
        private String path;

        /**
         * HTTP 메소드 (GET, POST, PUT, DELETE 등)
         */
        private String method;

        /**
         * 핸들러 빈 이름 (예: authHandler)
         */
        private String handler;

        /**
         * 핸들러 메소드 명 (예: login, refresh)
         */
        private String handlerMethod;

        /**
         * 설명
         */
        private String description;
    }

    /**
     * 프록시 라우트 설정
     */
    @Data
    public static class ProxyRoute {
        /**
         * 서비스 이름 (예: user-service)
         */
        private String serviceName;

        /**
         * 라우팅 경로 (예: /api/users/**)
         */
        private String path;

        /**
         * 지원하는 HTTP 메소드 리스트 (예: GET, POST, PUT, DELETE)
         */
        private List<String> methods;

        /**
         * 핸들러 메소드 명 (예: proxyToUserService)
         */
        private String handlerMethod;

        /**
         * 설명
         */
        private String description;
    }
}

