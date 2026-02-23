# Spring 기반 BFF API 설계 계획

## 1. BFF(Backend for Frontend) 패턴 개요

### 1.1 BFF란?
- **정의**: Frontend 클라이언트 각각에 최적화된 전용 Backend API
- **목적**: 복잡한 마이크로서비스 아키텍처를 Frontend에서 감추고, 필요한 데이터만 제공
- **핵심 가치**:
  - API 응답 형식을 Frontend 요구사항에 맞게 최적화
  - 토큰 관리와 인증을 서버에서 안전하게 처리
  - 여러 Backend 서비스의 데이터를 조합(API Composition)
  - 각 Frontend 팀이 자신의 BFF를 독립적으로 관리 가능

### 1.2 BFF vs API Gateway
| 구분 | API Gateway | BFF |
|------|----------|-----|
| **목적** | 모든 클라이언트를 위한 단일 진입점 | 특정 Frontend를 위한 전용 Backend |
| **책임** | 라우팅, 인증, Rate Limiting | API 조합, 데이터 변환, Frontend 최적화 |
| **개수** | 보통 1개 | Frontend 타입별로 여러 개 (Web, Mobile, TV 등) |
| **데이터 처리** | 최소한의 변환 | 적극적인 변환 및 조합 |

---

## 2. 국내외 주요 사례 분석

### 2.1 우아한형제들 (배달의민족)

**진화 과정:**
- **초기**: 모놀리틱 아키텍처
- **2017년**: Spring Cloud Zuul 1.0 도입
- **현재**: Spring Cloud Gateway로 마이그레이션

**주요 구현 내용:**
```
클라이언트 → API Gateway (Spring Cloud Gateway)
              ↓
         [인증 필터]
         [JWT 검증]
              ↓
    마이크로서비스들 (User, Order, Payment, ...)
```

**핵심 기능:**
- **Database 기반 동적 설정**: Hot reload 지원으로 서버 재시작 없이 라우팅 규칙 변경
- **필터 체인 분리**: Pre-filter, Post-filter로 유연한 처리
- **JWT 기반 인증**: 단일 인증 필터에서 모든 요청 검증
- **Rate Limiting & Circuit Breaker**: 흐름 제어로 시스템 안정성 확보
- **API Gateway에서의 인증 처리**: 각 마이크로서비스의 부담 감소

**참고 자료**: [배민 API GATEWAY – spring cloud zuul 적용기](https://techblog.woowahan.com/2523/)

---

### 2.2 Netflix 사례

**아키텍처 특징:**
- **Platform별 BFF**: Android, iOS, TV, Web 각각을 위한 전용 Backend
- **팀별 소유권**: 각 플랫폼 팀이 자신의 BFF를 관리 및 최적화

**마이크로서비스 스택:**
- **Eureka**: 서비스 디스커버리
- **Ribbon**: 클라이언트 사이드 로드 밸런싱
- **Hystrix**: 서킷 브레이커 패턴
- **Zuul**: API Gateway (현재는 Spring Cloud Gateway로 대체)

**진화**: Spring Cloud Netflix는 현재 유지보수 모드 → Spring Cloud Gateway 권장

**참고 자료**:
- [Netflix OSS 및 Spring Cloud 통합](https://spring.io/projects/spring-cloud-netflix/)
- [MicroServices Architecture - Spring Boot and Netflix Infrastructure](https://www.optisolbusiness.com/insight/micro-services-architecture-spring-boot-and-netflix-infrastructure)

---

### 2.3 국제 표준 패턴

**BFF + OAuth2 + Spring Security 아키텍처:**
```
React/Angular/Vue (브라우저)
        ↓ (쿠키 기반 세션)
Spring Cloud Gateway (BFF)
        ↓ (토큰 포워딩)
마이크로서비스 + Resource Server
        ↓
OAuth2 Authorization Server
```

**보안 이점:**
- Access Token & Refresh Token을 서버에서만 관리
- 쿠키는 HttpOnly + Secure 플래그로 설정
- Frontend는 토큰을 직접 다루지 않음 (XSS 공격 방지)

**주요 기술:**
- Spring Cloud Gateway의 `TokenRelay` 필터: OAuth2 인증된 토큰을 자동으로 Backend에 포워딩
- Spring Authorization Server: OAuth2 Authorization Endpoint 제공
- Keycloak 통합: 별도의 IAM 솔루션

**참고 자료**:
- [OAuth2 Backend for Frontend With Spring Cloud Gateway](https://www.baeldung.com/spring-cloud-gateway-bff-oauth2)
- [Secure SPA using BFF pattern with Spring Cloud Gateway](https://blog.nashtechglobal.com/secure-spa-using-bff-pattern-with-spring-cloud-gateway/)
- [Spring Cloud Gateway + Keycloak: a complete example](https://tekkix.com/articles/security/2025/01/spring-cloud-gateway-keycloak-a-complete)

---

## 3. Spring Cloud Gateway를 활용한 BFF 설계

### 3.1 핵심 구성 요소

```
┌─────────────────────────────────────────────────────┐
│          Frontend Applications                      │
│  (Web - React/Vue, Mobile - React Native, ...)     │
└────────────────────┬────────────────────────────────┘
                     │ (HTTP 요청)
┌────────────────────▼────────────────────────────────┐
│      Spring Cloud Gateway (BFF Layer)              │
├─────────────────────────────────────────────────────┤
│ 1. Route Predicate (경로 매칭)                     │
│    - Host, Path, Method, Header 기반 라우팅       │
│                                                    │
│ 2. Global Filters (전역 필터)                     │
│    - 인증/인가 검증                               │
│    - 요청/응답 로깅                               │
│    - Rate Limiting                                │
│                                                    │
│ 3. Custom Filters (커스텀 필터)                   │
│    - JWT 검증                                     │
│    - 요청 헤더 변환                               │
│    - 응답 데이터 변환                             │
│                                                    │
│ 4. API Composition                                |
│    - 여러 마이크로서비스 호출 조합                 │
│    - 데이터 변환 및 정렬                          │
└────────────────────┬────────────────────────────────┘
                     │ (라우팅된 요청)
        ┌────────────┼────────────┐
        ▼            ▼            ▼
    ┌────────┐  ┌────────┐  ┌────────┐
    │ User   │  │ Order  │  │Payment │
    │Service │  │Service │  │Service │
    └────────┘  └────────┘  └────────┘
```

### 3.2 설계 구조

#### 3.2.1 라우팅 계층

**설정 방식: YAML 기반 선언적 설정**

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
          filters:
            - StripPrefix=2
            - AuthFilter

        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=2
            - AuthFilter
            - RateLimiter=10
```

**라우팅 규칙:**
- `lb://` : Eureka 서비스 디스커버리를 통한 로드 밸런싱
- `Predicate` : 요청을 특정 Backend 서비스로 매칭
- `Filter` : 요청/응답 처리

#### 3.2.2 인증/인가 필터

**Pre-Filter에서 인증 검증:**

```java
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 1. 쿠키에서 액세스 토큰 추출
            String token = extractTokenFromCookie(request);

            // 2. JWT 검증
            if (!jwtTokenProvider.validateToken(token)) {
                return handleAuthError(exchange);
            }

            // 3. 사용자 정보를 헤더에 추가
            Claims claims = jwtTokenProvider.getClaims(token);
            ServerHttpRequest newRequest = request.mutate()
                .header("X-User-Id", claims.getSubject())
                .header("X-User-Roles", String.join(",", claims.get("roles")))
                .build();

            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }

    public static class Config {}
}
```

**보안 특징:**
- 쿠키 기반 액세스 토큰 (HttpOnly, Secure 플래그)
- 리프레시 토큰으로 토큰 갱신
- 요청 헤더에 사용자 정보 추가

#### 3.2.3 Rate Limiting 필터

```yaml
spring:
  cloud:
    gateway:
      default-filters:
        - name: RequestRateLimiter
          args:
            redis-rate-limiter.replenishRate: 100    # 초당 100개 요청
            redis-rate-limiter.burstCapacity: 200    # 버스트 용량
            key-resolver: "#{@userKeyResolver}"
```

#### 3.2.4 서킷 브레이커 (Resilience4j)

```yaml
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 10000

  retry:
    instances:
      user-service:
        maxAttempts: 3
        waitDuration: 1000
```

### 3.3 API 조합 (API Composition) 구현

**여러 마이크로서비스의 데이터 조합 예제:**

```java
@RestController
@RequestMapping("/api/bff")
public class BFFController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user-dashboard/{userId}")
    public CompletableFuture<DashboardResponse> getUserDashboard(@PathVariable String userId) {
        // 병렬로 여러 서비스 호출
        CompletableFuture<User> userFuture =
            CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://user-service/api/users/" + userId, User.class)
            );

        CompletableFuture<List<Order>> ordersFuture =
            CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://order-service/api/orders?userId=" + userId,
                    new ParameterizedTypeReference<List<Order>>() {})
            );

        CompletableFuture<List<Payment>> paymentsFuture =
            CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://payment-service/api/payments?userId=" + userId,
                    new ParameterizedTypeReference<List<Payment>>() {})
            );

        // 모든 호출 완료 대기 후 응답 조합
        return CompletableFuture.allOf(userFuture, ordersFuture, paymentsFuture)
            .thenApply(v -> new DashboardResponse(
                userFuture.join(),
                ordersFuture.join(),
                paymentsFuture.join()
            ));
    }
}
```

---

## 4. 구현 로드맵

### 4.1 1단계: 기본 Gateway 구성 (1-2주)
- [ ] Spring Cloud Gateway 기본 설정
- [ ] 마이크로서비스 라우팅 설정 (YAML 기반)
- [ ] Eureka를 통한 서비스 디스커버리 연동
- [ ] 기본 Pre-filter 및 Post-filter 구현

### 4.2 2단계: 인증/보안 구현 (2-3주)
- [ ] JWT 토큰 발급 및 검증 로직 구현
- [ ] 쿠키 기반 토큰 관리 (HttpOnly, Secure)
- [ ] AuthenticationFilter 구현
- [ ] OAuth2 / Spring Authorization Server 통합 (선택)
- [ ] CORS 정책 설정

### 4.3 3단계: 트래픽 관리 (1-2주)
- [ ] Rate Limiter 설정 (Redis 활용)
- [ ] Circuit Breaker (Resilience4j) 구현
- [ ] Retry 전략 설정
- [ ] 요청 타임아웃 정책

### 4.4 4단계: API 조합 및 변환 (2-3주)
- [ ] BFF Controller 구현 (API Composition)
- [ ] 응답 데이터 변환 로직
- [ ] 병렬 마이크로서비스 호출
- [ ] 에러 처리 및 Fallback 로직

### 4.5 5단계: 운영 및 모니터링 (1-2주)
- [ ] 요청/응답 로깅
- [ ] 구조화된 로깅 (SLF4J, Logback)
- [ ] 메트릭 수집 (Micrometer, Prometheus)
- [ ] 분산 트레이싱 (Spring Cloud Sleuth, Zipkin)
- [ ] 헬스체크 엔드포인트

---

## 5. 기술 스택 권장안

### 5.1 Core Framework
- **Spring Boot 3.x** : 최신 안정 버전
- **Spring Cloud 2024.x** : 최신 BOM (Bill of Materials)
- **Spring Cloud Gateway** : API 게이트웨이
- **Eureka Client** : 서비스 디스커버리 (또는 Consul/Nacos)

### 5.2 보안 & 인증
- **Spring Security 6.x** : 인증/인가
- **Spring Authorization Server** : OAuth2 Authorization Endpoint
- **Spring Data Redis** : 토큰 저장소 및 Rate Limiting

### 5.3 복원력 & 모니터링
- **Resilience4j** : Circuit Breaker, Retry, Rate Limiter
- **Spring Cloud Sleuth** : 분산 트레이싱
- **Micrometer** : 메트릭 수집
- **Prometheus & Grafana** : 모니터링 시스템

### 5.4 데이터 관리
- **Redis** : 세션 저장소, 토큰 캐시, Rate Limiter 상태
- **Spring Data JPA** (선택) : 동적 라우팅 규칙 저장소

---

## 6. 주의사항 및 모범 사례

### 6.1 성능 최적화
```
1. 비동기 처리: 여러 마이크로서비스 병렬 호출
2. 캐싱: 자주 호출되는 데이터는 Redis에 캐싱
3. 연결 풀링: 마이크로서비스 연결 재사용
4. 타임아웃 설정: 무한 대기 방지
```

### 6.2 보안 강화
```
1. 토큰 탈취 방지: HttpOnly 쿠키 사용
2. CORS 정책: 신뢰할 수 있는 Origin만 허용
3. 요청 검증: 입력 데이터 검증 (SQL Injection 방지)
4. 민감한 정보: 헤더에서 제거 후 전송
5. HTTPS: 모든 통신 암호화
```

### 6.3 운영 관리
```
1. 동적 설정: 서버 재시작 없이 라우팅 규칙 변경
2. 모니터링: 요청 지연, 에러율, 처리량 실시간 추적
3. 로깅: 모든 중요한 이벤트 기록 (감사 추적)
4. 알림: 임계값 초과 시 자동 알림
5. 카나리 배포: 새 버전을 일부 트래픽만 라우팅
```

---

## 7. 참고 자료

### 공식 문서
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Authorization Server](https://spring.io/projects/spring-authorization-server)

### 국내 참고 사례
- [배민 API GATEWAY – spring cloud zuul 적용기](https://techblog.woowahan.com/2523/)
- [우아콘 2020 배달의민족 마이크로서비스 여행기](https://velog.io/@ariul-dev/우아콘-2020-배달의민족-마이크로서비스-여행기-우아한형제들-배민서비스개발팀-김영한)

### 국제 표준 패턴 및 튜토리얼
- [OAuth2 Backend for Frontend With Spring Cloud Gateway - Baeldung](https://www.baeldung.com/spring-cloud-gateway-bff-oauth2)
- [Secure SPA using BFF pattern with Spring Cloud Gateway - NashTech Blog](https://blog.nashtechglobal.com/secure-spa-using-bff-pattern-with-spring-cloud-gateway/)
- [Spring Cloud Gateway + Keycloak Integration](https://tekkix.com/articles/security/2025/01/spring-cloud-gateway-keycloak-a-complete)
- [Sam Newman - Backends For Frontends](https://samnewman.io/patterns/architectural/bff/)
- [Microservices Pattern: API Gateway / Backends for Frontends](https://microservices.io/patterns/apigateway.html)

---

## 8. 다음 단계

이 계획을 바탕으로 다음을 진행할 수 있습니다:

1. **상세 설계서 작성**: 각 마이크로서비스 정의 및 API 스펙
2. **개발 환경 구성**: Docker Compose를 이용한 로컬 개발 환경
3. **프로토타입 구현**: 기본 Gateway + 2-3개 마이크로서비스로 MVP 구성
4. **팀 교육**: 개발자들을 위한 기술 워크숍
5. **일정 수립**: 각 단계별 마일스톤 정의

