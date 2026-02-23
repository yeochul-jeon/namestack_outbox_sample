# WebFlux + WebClient 기반 BFF API 신규 설계 계획

## 1. 설계 개요

### 1.1 핵심 아키텍처
```
┌─────────────────────────────────────────────────┐
│          Frontend Applications                  │
│  (Web - React/Vue, Mobile - React Native)      │
└────────────────────┬────────────────────────────┘
                     │ (HTTP/HTTPS)
┌────────────────────▼────────────────────────────┐
│      Spring WebFlux BFF Layer                   │
├─────────────────────────────────────────────────┤
│ 1. WebFilter Chain (필터 체인)                  │
│    - JwtAuthenticationFilter (JWT 검증)        │
│    - RateLimitingFilter (요청 제한)            │
│    - LoggingFilter (요청/응답 로깅)            │
│                                                 │
│ 2. Router Function / Controller                │
│    - 경로 기반 라우팅                          │
│    - 프록시 라우팅 (단순 전달)                 │
│    - API Composition (데이터 조합)             │
│                                                 │
│ 3. WebClient (HTTP Client)                     │
│    - 비동기 논블로킹 호출                      │
│    - Circuit Breaker 통합                      │
│    - Retry 전략                                │
│                                                 │
│ 4. Reactive 데이터 변환                        │
│    - Mono/Flux 기반 스트림 처리                │
└────────────────────┬────────────────────────────┘
                     │ (K8s Service Discovery)
        ┌────────────┼────────────┐
        ▼            ▼            ▼
    ┌────────┐  ┌────────┐  ┌────────┐
    │ User   │  │ Order  │  │Payment │
    │Service │  │Service │  │Service │
    └────────┘  └────────┘  └────────┘
      (K8s)       (K8s)       (K8s)
```

### 1.2 Spring Cloud Gateway를 사용하지 않는 이유와 장점

**Spring Cloud Gateway의 제약사항:**
- 프레임워크에 종속적인 설정 구조
- 복잡한 커스터마이징 시 오버헤드 증가
- 세밀한 제어가 어려움

**순수 WebFlux + WebClient의 장점:**
- **완전한 제어권**: 모든 로직을 직접 구현하여 세밀하게 제어
- **경량화**: 불필요한 Gateway 추상화 레이어 제거
- **유연성**: 비즈니스 로직과 라우팅을 자유롭게 조합
- **학습 곡선**: WebFlux와 Reactor만 이해하면 됨
- **K8s 친화적**: Service Discovery가 K8s 네이티브하게 동작

---

## 2. 핵심 컴포넌트 설계

### 2.1 WebFilter Chain (필터 체인)

WebFlux는 `WebFilter` 인터페이스로 요청/응답을 가로채는 필터 체인을 제공합니다.

#### 2.1.1 JWT 인증 필터

```java
@Component
@Order(1)
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 1. Authorization 헤더 또는 쿠키에서 토큰 추출
        String token = extractToken(request);

        // 2. JWT 검증
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // 3. 사용자 정보를 exchange attributes에 저장
        Claims claims = jwtTokenProvider.getClaims(token);
        exchange.getAttributes().put("userId", claims.getSubject());
        exchange.getAttributes().put("roles", claims.get("roles"));

        // 4. 다운스트림 서비스를 위한 헤더 추가
        ServerHttpRequest mutatedRequest = request.mutate()
            .header("X-User-Id", claims.getSubject())
            .header("X-User-Roles", String.join(",", (List) claims.get("roles")))
            .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }
}
```

#### 2.1.2 Rate Limiting 필터

**Bucket4j + Redis 활용:**

```java
@Component
@Order(2)
public class RateLimitingFilter implements WebFilter {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String userId = (String) exchange.getAttribute("userId");
        String rateLimitKey = "rate_limit:" + userId;

        // Redis 기반 Rate Limiting (Token Bucket 알고리즘)
        return redisTemplate.opsForValue().increment(rateLimitKey, 1)
            .flatMap(count -> {
                if (count > 100) { // 초당 100개 요청 제한
                    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                    return exchange.getResponse().setComplete();
                }
                return chain.filter(exchange);
            })
            .doFinally(signalType -> {
                // TTL 설정 (1초)
                redisTemplate.expire(rateLimitKey, Duration.ofSeconds(1)).subscribe();
            });
    }
}
```

#### 2.1.3 로깅 필터

```java
@Component
@Order(3)
@Slf4j
public class RequestLoggingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long startTime = System.currentTimeMillis();
        ServerHttpRequest request = exchange.getRequest();

        log.info("Request: {} {}", request.getMethod(), request.getURI());

        return chain.filter(exchange)
            .doFinally(signalType -> {
                long duration = System.currentTimeMillis() - startTime;
                log.info("Response: {} {} - {}ms",
                    request.getMethod(),
                    request.getURI(),
                    duration);
            });
    }
}
```

---

### 2.2 라우팅 계층

WebFlux는 두 가지 라우팅 방식을 제공합니다:
1. **RouterFunction** (함수형 엔드포인트) - 추천
2. **@RestController** (어노테이션 기반)

#### 2.2.1 RouterFunction 기반 라우팅 (추천)

```java
@Configuration
public class RouteConfiguration {

    @Bean
    public RouterFunction<ServerResponse> proxyRoutes(ProxyHandler proxyHandler) {
        return RouterFunctions.route()
            // 단순 프록시 라우팅
            .GET("/api/users/**", proxyHandler::proxyToUserService)
            .POST("/api/users/**", proxyHandler::proxyToUserService)
            .GET("/api/orders/**", proxyHandler::proxyToOrderService)

            // API Composition (데이터 조합)
            .GET("/api/dashboard/{userId}", proxyHandler::getUserDashboard)

            .build();
    }
}
```

#### 2.2.2 ProxyHandler 구현

```java
@Component
public class ProxyHandler {

    private final WebClient userServiceClient;
    private final WebClient orderServiceClient;
    private final WebClient paymentServiceClient;

    // K8s Service Discovery를 통한 WebClient 설정
    public ProxyHandler(WebClient.Builder webClientBuilder) {
        this.userServiceClient = webClientBuilder
            .baseUrl("http://user-service:8080") // K8s Service 이름
            .build();

        this.orderServiceClient = webClientBuilder
            .baseUrl("http://order-service:8080")
            .build();

        this.paymentServiceClient = webClientBuilder
            .baseUrl("http://payment-service:8080")
            .build();
    }

    // 단순 프록시: User Service로 요청 전달
    public Mono<ServerResponse> proxyToUserService(ServerRequest request) {
        String path = request.path().replace("/api/users", "");

        return userServiceClient
            .method(request.method())
            .uri(path)
            .headers(headers -> headers.addAll(request.headers().asHttpHeaders()))
            .bodyValue(request.bodyToMono(String.class))
            .retrieve()
            .bodyToMono(String.class)
            .flatMap(body -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body));
    }

    // API Composition: 여러 서비스 병렬 호출 및 조합
    @CircuitBreaker(name = "dashboard", fallbackMethod = "dashboardFallback")
    public Mono<ServerResponse> getUserDashboard(ServerRequest request) {
        String userId = request.pathVariable("userId");

        // 병렬 호출
        Mono<User> userMono = userServiceClient.get()
            .uri("/users/" + userId)
            .retrieve()
            .bodyToMono(User.class);

        Mono<List<Order>> ordersMono = orderServiceClient.get()
            .uri("/orders?userId=" + userId)
            .retrieve()
            .bodyToFlux(Order.class)
            .collectList();

        Mono<List<Payment>> paymentsMono = paymentServiceClient.get()
            .uri("/payments?userId=" + userId)
            .retrieve()
            .bodyToFlux(Payment.class)
            .collectList();

        // 모든 호출을 병렬로 실행하고 결과 조합
        return Mono.zip(userMono, ordersMono, paymentsMono)
            .map(tuple -> new DashboardResponse(
                tuple.getT1(),  // User
                tuple.getT2(),  // Orders
                tuple.getT3()   // Payments
            ))
            .flatMap(dashboard -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dashboard));
    }

    // Circuit Breaker Fallback
    public Mono<ServerResponse> dashboardFallback(ServerRequest request, Exception ex) {
        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
            .bodyValue(Map.of("error", "Dashboard service temporarily unavailable"));
    }
}
```

---

### 2.3 WebClient 설정 (Circuit Breaker + Retry)

#### 2.3.1 WebClient 빈 설정

```java
@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient.Builder webClientBuilder(
            ReactorLoadBalancerExchangeFilterFunction loadBalancerFunction,
            ObservationRegistry observationRegistry) {

        return WebClient.builder()
            // K8s Service Discovery
            .filter(loadBalancerFunction)

            // 분산 트레이싱 (Micrometer Tracing)
            .observationRegistry(observationRegistry)

            // 타임아웃 설정
            .clientConnector(new ReactorClientHttpConnector(
                HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .responseTimeout(Duration.ofSeconds(10))
                    .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(10))
                            .addHandlerLast(new WriteTimeoutHandler(10))
                    )
            ))

            // 기본 헤더
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    // K8s Service Discovery를 위한 LoadBalancer 설정
    @Bean
    @LoadBalanced
    public ReactorLoadBalancerExchangeFilterFunction loadBalancerFunction(
            ReactorLoadBalancerExchangeFilterFunction.Factory factory) {
        return factory.create();
    }
}
```

#### 2.3.2 Resilience4j Circuit Breaker 설정

```yaml
# application.yml
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        sliding-window-size: 10
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10000ms
        permitted-number-of-calls-in-half-open-state: 3
        automatic-transition-from-open-to-half-open-enabled: true

      order-service:
        sliding-window-size: 10
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10000ms

      payment-service:
        sliding-window-size: 10
        failure-rate-threshold: 50
        wait-duration-in-open-state: 10000ms

  retry:
    instances:
      user-service:
        max-attempts: 3
        wait-duration: 1000ms
        enable-exponential-backoff: true
        exponential-backoff-multiplier: 2

      order-service:
        max-attempts: 3
        wait-duration: 1000ms

      payment-service:
        max-attempts: 3
        wait-duration: 1000ms
```

---

### 2.4 K8s Service Discovery 통합

Kubernetes 환경에서는 Service 이름으로 자동 DNS 기반 디스커버리가 가능합니다.

#### 2.4.1 K8s Service 예제

```yaml
# user-service.yaml
apiVersion: v1
kind: Service
metadata:
  name: user-service
  namespace: default
spec:
  selector:
    app: user-service
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080
  type: ClusterIP
```

#### 2.4.2 BFF에서 K8s Service 호출

```java
// WebClient가 "http://user-service:8080"로 호출하면
// K8s DNS가 자동으로 user-service의 ClusterIP로 라우팅
WebClient.create("http://user-service:8080")
    .get()
    .uri("/users/123")
    .retrieve()
    .bodyToMono(User.class);
```

---

## 3. 분산 트레이싱 & 모니터링

### 3.1 Micrometer Tracing + Zipkin

```yaml
# application.yml
management:
  tracing:
    sampling:
      probability: 1.0  # 100% 샘플링 (운영에서는 0.1 권장)
  zipkin:
    tracing:
      endpoint: http://zipkin:9411/api/v2/spans

  metrics:
    export:
      prometheus:
        enabled: true

  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
```

### 3.2 프로메테우스 메트릭 수집

```java
@Configuration
public class MetricsConfiguration {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
            .commonTags("application", "bff-service");
    }
}
```

---

## 4. JWT 인증 구현

### 4.1 JWT 토큰 Provider

```java
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity}")
    private long accessTokenValidity;

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidity;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenValidity);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
```

### 4.2 로그인 엔드포인트

```java
@Component
public class AuthHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public Mono<ServerResponse> login(ServerRequest request) {
        return request.bodyToMono(LoginRequest.class)
            .flatMap(loginRequest -> {
                // 사용자 인증 로직 (User Service 호출 또는 로컬 검증)
                return authenticateUser(loginRequest.getUsername(), loginRequest.getPassword())
                    .flatMap(user -> {
                        // 토큰 생성
                        String accessToken = jwtTokenProvider.createAccessToken(
                            user.getId(), user.getRoles()
                        );
                        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

                        // Refresh Token을 Redis에 저장
                        return redisTemplate.opsForValue()
                            .set("refresh_token:" + user.getId(), refreshToken,
                                Duration.ofDays(7))
                            .then(ServerResponse.ok()
                                .cookie(ResponseCookie.from("accessToken", accessToken)
                                    .httpOnly(true)
                                    .secure(true)
                                    .path("/")
                                    .maxAge(Duration.ofHours(1))
                                    .sameSite("Strict")
                                    .build())
                                .bodyValue(Map.of(
                                    "userId", user.getId(),
                                    "username", user.getUsername(),
                                    "roles", user.getRoles()
                                ))
                            );
                    });
            });
    }
}
```

---

## 5. 프로젝트 구조

```
bff-service/
├── src/main/java/com/example/bff/
│   ├── BffApplication.java
│   │
│   ├── config/
│   │   ├── WebClientConfiguration.java
│   │   ├── RouteConfiguration.java
│   │   ├── SecurityConfiguration.java
│   │   └── MetricsConfiguration.java
│   │
│   ├── filter/
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── RateLimitingFilter.java
│   │   └── RequestLoggingFilter.java
│   │
│   ├── handler/
│   │   ├── ProxyHandler.java
│   │   ├── AuthHandler.java
│   │   └── CompositionHandler.java
│   │
│   ├── service/
│   │   ├── JwtTokenProvider.java
│   │   └── UserAuthenticationService.java
│   │
│   ├── dto/
│   │   ├── LoginRequest.java
│   │   ├── DashboardResponse.java
│   │   ├── User.java
│   │   ├── Order.java
│   │   └── Payment.java
│   │
│   └── exception/
│       ├── GlobalExceptionHandler.java
│       └── BffException.java
│
├── src/main/resources/
│   ├── application.yml
│   └── application-prod.yml
│
└── pom.xml
```

---

## 6. 기술 스택

### 6.1 Core Framework
- **Spring Boot 3.4.x** (최신 안정 버전)
- **Spring WebFlux** (Reactive Web Framework)
- **Project Reactor** (Mono/Flux 기반 비동기 처리)
- **Spring Cloud Kubernetes** (K8s Service Discovery)

### 6.2 HTTP Client
- **WebClient** (비동기 논블로킹 HTTP 클라이언트)

### 6.3 보안 & 인증
- **Spring Security Reactive** (WebFlux용 보안)
- **jjwt** (JWT 토큰 생성/검증)
- **Spring Data Redis Reactive** (토큰 저장, Rate Limiting)

### 6.4 복원력
- **Resilience4j** (Circuit Breaker, Retry, Rate Limiter)
- **Bucket4j** (Rate Limiting - 선택)

### 6.5 모니터링 & 트레이싱
- **Micrometer** (메트릭 수집)
- **Micrometer Tracing** (분산 트레이싱)
- **Zipkin** (트레이싱 시각화)
- **Prometheus** (메트릭 저장소)

### 6.6 데이터 저장소
- **Redis** (토큰 캐시, Rate Limiting, 세션)

---

## 7. 구현 로드맵

### Phase 1: 기본 WebFlux BFF 구성
**목표**: 기본 프록시 라우팅 동작

1. Spring Boot 프로젝트 생성 (WebFlux 의존성)
2. RouterFunction 기반 라우팅 설정
3. WebClient 설정 (K8s Service Discovery)
4. 단순 프록시 핸들러 구현 (User/Order/Payment Service)
5. 로컬 테스트 (Mock 서버)

**주요 파일**:
- `RouteConfiguration.java`
- `WebClientConfiguration.java`
- `ProxyHandler.java`

---

### Phase 2: JWT 인증 구현
**목표**: 쿠키 기반 JWT 인증/인가

1. JWT 토큰 Provider 구현
2. JwtAuthenticationFilter 구현 (WebFilter)
3. 로그인 엔드포인트 구현 (AccessToken + RefreshToken)
4. Redis 연동 (Refresh Token 저장)
5. 토큰 갱신 엔드포인트

**주요 파일**:
- `JwtTokenProvider.java`
- `JwtAuthenticationFilter.java`
- `AuthHandler.java`

---

### Phase 3: Rate Limiting & Circuit Breaker
**목표**: 트래픽 제어 및 장애 전파 방지

1. Rate Limiting Filter 구현 (Redis 기반)
2. Resilience4j Circuit Breaker 설정
3. Retry 전략 설정
4. Fallback 로직 구현

**주요 파일**:
- `RateLimitingFilter.java`
- `application.yml` (Resilience4j 설정)

---

### Phase 4: API Composition
**목표**: 여러 마이크로서비스 병렬 호출 및 데이터 조합

1. Dashboard API 구현 (User + Order + Payment 조합)
2. Mono.zip을 활용한 병렬 호출
3. 에러 핸들링 (일부 서비스 실패 시 처리)
4. 응답 캐싱 (Redis)

**주요 파일**:
- `CompositionHandler.java`
- `DashboardResponse.java`

---

### Phase 5: 분산 트레이싱 & 모니터링
**목표**: 운영 환경 준비

1. Micrometer Tracing 설정
2. Zipkin 연동
3. Prometheus 메트릭 수집
4. 구조화된 로깅 (Logback + JSON 포맷)
5. 헬스체크 엔드포인트

**주요 파일**:
- `MetricsConfiguration.java`
- `RequestLoggingFilter.java`
- `application.yml` (Tracing 설정)

---

### Phase 6: K8s 배포
**목표**: Kubernetes 환경 배포

1. Dockerfile 작성
2. K8s Deployment 매니페스트
3. K8s Service 매니페스트
4. ConfigMap (설정 외부화)
5. Secret (JWT Secret, Redis Password)
6. Ingress 설정

---

## 8. 주요 의존성 (pom.xml)

```xml
<dependencies>
    <!-- Spring WebFlux -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <!-- Spring Security Reactive -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Data Redis Reactive -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
    </dependency>

    <!-- Spring Cloud Kubernetes (Service Discovery) -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-kubernetes-fabric8-all</artifactId>
    </dependency>

    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.3</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.3</version>
        <scope>runtime</scope>
    </dependency>

    <!-- Resilience4j -->
    <dependency>
        <groupId>io.github.resilience4j</groupId>
        <artifactId>resilience4j-spring-boot3</artifactId>
        <version>2.1.0</version>
    </dependency>
    <dependency>
        <groupId>io.github.resilience4j</groupId>
        <artifactId>resilience4j-reactor</artifactId>
        <version>2.1.0</version>
    </dependency>

    <!-- Micrometer Tracing -->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-tracing-bridge-brave</artifactId>
    </dependency>
    <dependency>
        <groupId>io.zipkin.reporter2</groupId>
        <artifactId>zipkin-reporter-brave</artifactId>
    </dependency>

    <!-- Micrometer Prometheus -->
    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
    </dependency>

    <!-- Actuator -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```

---

## 9. Spring Cloud Gateway vs WebFlux 비교

| 항목 | Spring Cloud Gateway | 순수 WebFlux + WebClient |
|------|---------------------|-------------------------|
| **추상화 수준** | 높음 (선언적 설정) | 낮음 (직접 구현) |
| **학습 곡선** | Gateway DSL 학습 필요 | WebFlux/Reactor만 이해 |
| **유연성** | 제한적 (프레임워크 규칙 내) | 완전한 제어 가능 |
| **코드량** | 적음 (설정 기반) | 많음 (코드 기반) |
| **커스터마이징** | Filter 확장 | 모든 로직 직접 구현 |
| **성능** | 우수 | 우수 (동일한 WebFlux 기반) |
| **K8s 통합** | 별도 설정 필요 | 네이티브 지원 |
| **적합한 경우** | 표준 Gateway 패턴 | 복잡한 비즈니스 로직 |

---

## 10. 핵심 설계 결정 사항

### 10.1 RouterFunction vs @RestController

**RouterFunction 선택 이유:**
- 함수형 프로그래밍 스타일로 라우팅 설정
- 타입 안전성 (컴파일 타임 체크)
- 테스트 용이성
- WebFlux의 철학과 일치

### 10.2 Rate Limiting 구현 방식

**Redis 기반 Token Bucket 선택:**
- 분산 환경에서 일관된 제한
- 다중 BFF 인스턴스 지원
- 실시간 카운팅

### 10.3 Circuit Breaker

**Resilience4j 선택:**
- Spring Cloud Netflix Hystrix는 유지보수 모드
- Reactor 네이티브 통합
- 경량 라이브러리

---

## 11. 보안 고려사항

1. **JWT 토큰 보호**:
   - HttpOnly 쿠키로 XSS 공격 방지
   - Secure 플래그로 HTTPS만 허용
   - SameSite=Strict로 CSRF 방지

2. **CORS 정책**:
   - 신뢰할 수 있는 Origin만 허용
   - Credentials 허용 시 와일드카드 금지

3. **입력 검증**:
   - 모든 요청 파라미터 검증
   - SQL Injection, XSS 방지

4. **민감한 정보**:
   - 헤더에서 내부 정보 제거
   - 에러 메시지에 스택 트레이스 노출 금지

---

## 12. 다음 단계

1. **MVP 구현**: Phase 1~2 구현하여 기본 인증 + 프록시 동작 검증
2. **로컬 테스트**: Docker Compose로 Redis + Mock 서비스 구성
3. **K8s 배포**: Minikube 또는 개발 클러스터에 배포
4. **성능 테스트**: JMeter/Gatling으로 부하 테스트
5. **운영 준비**: 모니터링, 알림, 로깅 시스템 구축
