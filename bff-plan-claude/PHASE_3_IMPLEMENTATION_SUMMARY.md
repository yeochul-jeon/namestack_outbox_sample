# Phase 3 Rate Limiting & Circuit Breaker 구현 요약

## 개요

Phase 3에서 트래픽 제어(Rate Limiting)와 장애 격리(Circuit Breaker) 기능을 구현했습니다. 이를 통해 BFF 서비스의 안정성과 복원력을 향상시켰습니다.

---

## 구현된 기능

### 1. Rate Limiting (RateLimitingFilter)

**파일:** `src/main/java/com/example/bff/filter/RateLimitingFilter.java`

**특징:**
- Redis 기반 Token Bucket 알고리즘
- 사용자별 요청 수 제한 (초당)
- 공개 엔드포인트 제외 (/login, /health 등)
- 429 Too Many Requests 응답
- Redis 오류 시 fail-open (요청 허용)

**설정:**
```yaml
rate-limit:
  enabled: true
  requests-per-second: 100  # 프로덕션
  requests-per-second: 1000 # 로컬 테스트
```

**동작 원리:**
```
1. 요청 수신 (JwtAuthenticationFilter 이후)
2. userId를 Key로 Redis 카운트 증가
3. 카운트 > 제한값 → 429 응답
4. 첫 요청 시 TTL 1초로 설정
```

---

### 2. Circuit Breaker (Resilience4j)

**설정:** `application.yml`, `application-local.yml`, `application-docker.yml`

**인스턴스:**
- user-service
- order-service
- payment-service

**핵심 설정:**
```yaml
circuitbreaker:
  instances:
    user-service:
      slidingWindowSize: 10              # 최근 10개 호출 평가
      minimumNumberOfCalls: 5            # 최소 5개 호출 필요
      failureRateThreshold: 50           # 50% 실패율에서 OPEN
      waitDurationInOpenState: 10000ms   # 10초 후 Half-Open
      automaticTransitionFromOpenToHalfOpenEnabled: true
```

**상태 전이:**
```
CLOSED (정상) → OPEN (장애) → HALF_OPEN (회복 시도) → CLOSED
```

---

### 3. Retry (Resilience4j)

**설정:** `application.yml`, `application-local.yml`, `application-docker.yml`

**핵심 설정:**
```yaml
retry:
  instances:
    user-service:
      maxAttempts: 3              # 최대 3회 재시도
      waitDuration: 1000ms        # 첫 재시도 간격
      enableExponentialBackoff: true
      exponentialBackoffMultiplier: 2  # 지수 백오프
```

**재시도 간격:** 1초, 2초, 4초, ...

---

## 구현 상세

### RateLimitingFilter 구현

**파일 경로:** `src/main/java/com/example/bff/filter/RateLimitingFilter.java`

**주요 메서드:**
```java
@Component
@Order(2)  // JwtAuthenticationFilter 이후 실행
public class RateLimitingFilter implements WebFilter {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 공개 엔드포인트 스킵
        if (isPublicEndpoint(path)) {
            return chain.filter(exchange);
        }

        String userId = exchange.getAttribute("userId");
        String rateLimitKey = "rate_limit:" + userId;

        // Redis 카운트 증가
        return redisTemplate.opsForValue().increment(rateLimitKey, 1)
            .flatMap(count -> {
                if (count > requestsPerSecond) {
                    return ServerResponse.status(429).complete();
                }
                if (count == 1) {
                    redisTemplate.expire(rateLimitKey, Duration.ofSeconds(1))
                        .subscribe();
                }
                return chain.filter(exchange);
            });
    }
}
```

---

### ProxyHandler 업데이트

**파일 경로:** `src/main/java/com/example/bff/handler/ProxyHandler.java`

**변경사항:**
```java
// Circuit Breaker와 Retry 레지스트리 주입
private final CircuitBreakerRegistry circuitBreakerRegistry;
private final RetryRegistry retryRegistry;

// Resilience4j Operator 적용
private Mono<ServerResponse> proxyRequest(..., String serviceName) {
    CircuitBreaker circuitBreaker = circuitBreakerRegistry
        .circuitBreaker(serviceName);
    Retry retry = retryRegistry.retry(serviceName);

    return webClient.method(...)
        .uri(...)
        .retrieve()
        .bodyToMono(String.class)
        // Retry → CircuitBreaker 순서로 적용
        .transformDeferred(RetryOperator.of(retry))
        .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
        .flatMap(...)
        .onErrorResume(...);
}
```

---

## 의존성 추가

**pom.xml 변경:**
```xml
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
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-annotations</artifactId>
    <version>2.1.0</version>
</dependency>
```

---

## 설정 파일 업데이트

### application.yml (프로덕션)
- Rate Limiting: 100 requests/second
- Circuit Breaker 인스턴스 3개 설정
- Retry 설정 (최대 3회, 지수 백오프)

### application-local.yml (로컬 개발)
- Rate Limiting: 1000 requests/second (테스트용 높게)
- Circuit Breaker: 빠른 전이 (5초 대기)
- Retry: 2회 (빠른 테스트)

### application-docker.yml (Docker Compose)
- Redis 연결: redis:6379
- Rate Limiting: 100 requests/second
- Circuit Breaker/Retry: 로컬과 유사

---

## Docker Compose 업데이트

**변경사항:**
```yaml
services:
  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"

  bff-service:
    depends_on:
      redis:
        condition: service_healthy
    environment:
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379
```

---

## 테스트 자동화

### 생성된 파일
1. **PHASE_3_TEST_GUIDE.md**: 상세 테스트 가이드
2. **test-phase3.sh**: 자동 테스트 스크립트

### 테스트 시나리오
- ✅ 정상 요청 (200 OK)
- ✅ Rate Limit 초과 (429 Too Many Requests)
- ✅ 공개 엔드포인트 (Rate Limit 미적용)
- ✅ Circuit Breaker CLOSED 상태
- ✅ Circuit Breaker OPEN 상태
- ✅ Circuit Breaker 회복 (Half-Open → Closed)
- ✅ Retry 메커니즘
- ✅ Redis Rate Limit 데이터 저장

---

## 성능 지표

### Rate Limiting (로컬)
```
redis.info("rate_limit:user-001")  # 증가된 카운트 확인
redis.ttl("rate_limit:user-001")   # TTL: 1초
```

### Circuit Breaker (로컬)
```
Sliding Window Size: 10개 호출
Minimum Calls: 3개
Failure Rate Threshold: 50%
Wait Duration (Open→Half-Open): 5초
```

### Retry (로컬)
```
Max Attempts: 2회
Initial Wait: 500ms
Exponential Backoff: 2배
```

---

## 모니터링 및 로깅

### 주요 로그 메시지

**Rate Limiting:**
```
Rate limit exceeded for userId: user-001 (count: 101)
```

**Circuit Breaker:**
```
Circuit breaker user-service is now OPEN
Circuit breaker user-service is now HALF_OPEN
Circuit breaker user-service is now CLOSED
```

**Retry:**
```
Retrying request (attempt 1/3) after 500ms
Retrying request (attempt 2/3) after 1000ms
```

### Health Check
```bash
curl http://localhost:8080/actuator/health | jq
# 응답에 Circuit Breaker 상태 포함
```

---

## 아키텍처 흐름

```
┌─────────────────────────────┐
│   Client Request            │
│ (GET /api/users/user-001)   │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│ 1. JwtAuthenticationFilter (Order 1)    │
│    - 토큰 검증                          │
│    - userId 추출                        │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│ 2. RateLimitingFilter (Order 2)         │
│    - Redis 기반 카운트 증가             │
│    - 제한 확인 (count > limit)          │
│    - 429 응답 또는 통과                 │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│ 3. RouteConfiguration                   │
│    - /api/users/** → ProxyHandler       │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│ 4. ProxyHandler                         │
│    - WebClient 요청 생성                │
│    - Retry 적용                         │
│    - Circuit Breaker 적용               │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│ 5. Downstream Service                   │
│    (User Service 8081)                  │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│ Response to Client                      │
│ (200 OK 또는 503 Service Unavailable)   │
└─────────────────────────────────────────┘
```

---

## 다음 단계

### Phase 4: API Composition
- 여러 서비스 병렬 호출
- 데이터 조합 및 변환
- 부분 실패 처리

### Phase 5: Monitoring & Tracing
- Micrometer Tracing
- Zipkin 연동
- Prometheus 메트릭

### Phase 6: Kubernetes 배포
- K8s Deployment
- Service & Ingress
- ConfigMap & Secret

---

## 코드 리뷰 체크리스트

✅ RateLimitingFilter 구현
- [x] Redis 연결 확인
- [x] 사용자별 제한 적용
- [x] 공개 엔드포인트 제외
- [x] fail-open 처리 (Redis 오류 시)
- [x] TTL 설정

✅ Circuit Breaker 설정
- [x] 3개 서비스 인스턴스 정의
- [x] Sliding Window 설정
- [x] Failure Rate Threshold 설정
- [x] Half-Open 자동 전이 활성화

✅ Retry 설정
- [x] 최대 재시도 횟수 설정
- [x] 지수 백오프 활성화
- [x] 재시도 간격 설정

✅ ProxyHandler 업데이트
- [x] CircuitBreakerRegistry 주입
- [x] RetryRegistry 주입
- [x] Resilience4j Operator 적용
- [x] 에러 처리

✅ Docker Compose 업데이트
- [x] Redis 서비스 추가
- [x] BFF 의존성 설정
- [x] 헬스 체크 설정

✅ 테스트 자동화
- [x] PHASE_3_TEST_GUIDE.md 작성
- [x] test-phase3.sh 스크립트 생성

---

## 트러블슈팅 가이드

### Issue: Rate Limit이 작동하지 않음
**해결:**
```bash
# Redis 연결 확인
docker exec bff-redis redis-cli ping

# Rate Limiting 활성화 확인
curl http://localhost:8080/health | jq '.details'
```

### Issue: Circuit Breaker가 OPEN되지 않음
**해결:**
- 최소 호출 수(minimumNumberOfCalls) 확인
- 실패율(failureRateThreshold) 확인
- 서비스 장애 확인 (실제로 실패하는지)

### Issue: Retry가 실행되지 않음
**해결:**
- 재시도 가능한 예외 확인
- 로그에서 "Retrying..." 메시지 확인

---

## 참고 자료

- [Resilience4j Documentation](https://resilience4j.readme.io/)
- [Spring Data Redis](https://spring.io/projects/spring-data-redis)
- [Project Reactor](https://projectreactor.io/)

---

## 변경 사항 요약

| 파일 | 변경사항 | 버전 |
|------|--------|------|
| pom.xml | Resilience4j 의존성 추가 | 2.1.0 |
| RateLimitingFilter.java | 신규 생성 | - |
| ProxyHandler.java | Circuit Breaker/Retry 적용 | - |
| application.yml | Rate Limit & Resilience4j 설정 | - |
| application-local.yml | 로컬 테스트 설정 | - |
| application-docker.yml | Docker 환경 설정 | - |
| docker-compose.yml | Redis 서비스 추가 | - |
| PHASE_3_TEST_GUIDE.md | 테스트 가이드 | - |
| test-phase3.sh | 자동 테스트 스크립트 | - |

---

**Phase 3 구현 완료 ✓**
