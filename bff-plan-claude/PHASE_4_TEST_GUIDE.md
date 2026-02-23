# Phase 4: API Composition 테스트 가이드

## 개요

Phase 4는 여러 마이크로서비스(User, Order, Payment)를 병렬로 호출하여 데이터를 조합하는 Dashboard API를 구현합니다.

## 1. 준비 사항

### 1.1 환경 설정

```bash
# 프로젝트 디렉토리로 이동
cd /Users/cjenm/project/bff-plan-claude

# 필수 요소 확인
- Redis 실행 (로컬): redis-server
- 백엔드 BFF 서비스 빌드 완료
- Mock 마이크로서비스 엔드포인트 준비
```

### 1.2 사전 조건

- Spring Boot 3.4.0 이상
- Java 17 이상
- Maven 3.8+
- Redis (Rate Limiting 및 토큰 저장용)

## 2. 구현된 기능

### 2.1 새로 추가된 DTO 모델

#### User.java
```
- id: String (사용자 ID)
- name: String (이름)
- email: String (이메일)
- role: String (역할)
- createdAt: String (생성 일시)
```

#### Order.java
```
- id: String (주문 ID)
- userId: String (사용자 ID)
- items: List<OrderItem> (주문 항목 리스트)
  - productId: String
  - quantity: int
  - price: double
- total: double (주문 총액)
- status: String (주문 상태)
- createdAt: String (생성 일시)
```

#### Payment.java
```
- id: String (결제 ID)
- userId: String (사용자 ID)
- orderId: String (주문 ID)
- amount: double (결제액)
- method: String (결제 방법)
- status: String (결제 상태: "completed", "pending", "failed")
- createdAt: String (생성 일시)
```

#### DashboardResponse.java
```
- user: User (사용자 정보)
- orders: List<Order> (주문 리스트)
- payments: List<Payment> (결제 리스트)
- summary: DashboardSummary (요약 정보)
  - totalOrders: int (총 주문 수)
  - totalSpent: double (총 지출액)
  - completedPayments: int (완료된 결제 수)
```

### 2.2 CompositionHandler 구현

**주요 메서드:**

- `getDashboard(ServerRequest)` - 대시보드 데이터 조회
  - userId를 경로 파라미터로 추출
  - User, Order, Payment 서비스 병렬 호출 (Mono.zip)
  - 결과를 DashboardResponse로 조합
  - Circuit Breaker + Retry 적용
  - 부분 실패 시 빈 데이터 반환 (Graceful Degradation)

- `getUserInfo(String userId)` - User 서비스 호출
- `getUserOrders(String userId)` - Order 서비스 호출
- `getUserPayments(String userId)` - Payment 서비스 호출

### 2.3 Resilience4j 설정

**Circuit Breaker 설정 (dashboard)**:
```yaml
slidingWindowSize: 10
minimumNumberOfCalls: 3
failureRateThreshold: 50%
permittedNumberOfCallsInHalfOpenState: 2
waitDurationInOpenState: 5000ms (로컬), 10000ms (운영)
```

**Retry 설정 (dashboard)**:
```yaml
maxAttempts: 2
waitDuration: 500ms
exponentialBackoff: enabled
exponentialBackoffMultiplier: 2
```

## 3. 테스트 시나리오

### 3.1 환경 구성

#### 3.1.1 로컬 Redis 실행
```bash
# Redis 실행 (포트 6379)
redis-server

# 또는 Docker 사용
docker run -d -p 6379:6379 redis:latest
```

#### 3.1.2 Mock 마이크로서비스 시작

**User Service (포트 8081)**:
```bash
# 터미널 1에서 실행
# /mock/user-service 디렉토리에서
node server.js
```

**Order Service (포트 8082)**:
```bash
# 터미널 2에서 실행
# /mock/order-service 디렉토리에서
node server.js
```

**Payment Service (포트 8083)**:
```bash
# 터미널 3에서 실행
# /mock/payment-service 디렉토리에서
node server.js
```

#### 3.1.3 BFF 서비스 시작
```bash
# 터미널 4에서 실행
cd /Users/cjenm/project/bff-plan-claude/bff-service
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"

# 또는
java -jar target/bff-service-1.0.0.jar --spring.profiles.active=local
```

BFF 서비스 시작 확인:
```bash
curl http://localhost:8080/health
# 응답: {"status":"UP","service":"bff-service"}
```

### 3.2 로그인 및 JWT 토큰 획득

**1단계: 로그인**
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john",
    "password": "password123"
  }' | jq .

# 응답 예시:
# {
#   "userId": "user-001",
#   "username": "john",
#   "roles": ["USER"]
# }
```

**2단계: JWT 토큰 확인**
- 응답 쿠키에 accessToken이 httpOnly로 저장됨
- 또는 환경변수에 저장:
```bash
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"password123"}' \
  -c /tmp/cookies.txt | jq -r '.userId')

echo "Token acquired for user: $TOKEN"
```

### 3.3 테스트 케이스

#### 테스트 1: 정상 동작 (모든 서비스 정상)

**테스트 목표**: Dashboard API가 3개 서비스 데이터를 성공적으로 조합하는지 확인

**요청**:
```bash
curl -X GET "http://localhost:8080/api/dashboard/user-001" \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Accept: application/json" | jq .
```

**예상 응답**:
```json
{
  "user": {
    "id": "user-001",
    "name": "John Doe",
    "email": "john@example.com",
    "role": "USER",
    "createdAt": "2025-01-01T00:00:00"
  },
  "orders": [
    {
      "id": "order-001",
      "userId": "user-001",
      "items": [
        {
          "productId": "prod-001",
          "quantity": 2,
          "price": 29.99
        }
      ],
      "total": 59.98,
      "status": "delivered",
      "createdAt": "2025-01-05T10:30:00"
    }
  ],
  "payments": [
    {
      "id": "payment-001",
      "userId": "user-001",
      "orderId": "order-001",
      "amount": 59.98,
      "method": "credit_card",
      "status": "completed",
      "createdAt": "2025-01-05T10:31:00"
    }
  ],
  "summary": {
    "totalOrders": 1,
    "totalSpent": 59.98,
    "completedPayments": 1
  }
}
```

**검증 기준**:
- ✓ HTTP 200 OK
- ✓ user 객체가 정상적으로 포함됨
- ✓ orders 배열이 정상적으로 포함됨
- ✓ payments 배열이 정상적으로 포함됨
- ✓ summary가 자동으로 계산됨
- ✓ 응답 시간 < 2초 (병렬 호출)

---

#### 테스트 2: 부분 실패 - Order Service 다운

**테스트 목표**: Order Service가 다운되었을 때 User와 Payment 데이터는 반환하는지 확인

**준비**:
```bash
# Order Service 중지 (CTRL+C)
# 또는 포트 8082를 차단
```

**요청**:
```bash
curl -X GET "http://localhost:8080/api/dashboard/user-001" \
  -H "Authorization: Bearer <JWT_TOKEN>" | jq .
```

**예상 응답** (부분 성공):
```json
{
  "user": {
    "id": "user-001",
    "name": "John Doe",
    "email": "john@example.com",
    "role": "USER",
    "createdAt": "2025-01-01T00:00:00"
  },
  "orders": [],
  "payments": [
    {
      "id": "payment-001",
      "userId": "user-001",
      "orderId": "order-001",
      "amount": 59.98,
      "method": "credit_card",
      "status": "completed",
      "createdAt": "2025-01-05T10:31:00"
    }
  ],
  "summary": {
    "totalOrders": 0,
    "totalSpent": 0.0,
    "completedPayments": 1
  }
}
```

**검증 기준**:
- ✓ HTTP 200 OK (부분 실패도 200 반환)
- ✓ user 데이터는 정상 반환
- ✓ orders는 빈 배열 []로 반환
- ✓ payments는 정상 반환
- ✓ summary는 부분 데이터로 재계산됨

**로그 확인**:
```bash
# 로그에 아래와 같은 경고 메시지 나타남
# WARN: Failed to fetch orders for userId: user-001, error: Connection refused
```

---

#### 테스트 3: 부분 실패 - User Service 타임아웃

**테스트 목표**: User Service가 3초 이상 응답하지 않을 때 동작 확인

**준비** (Mock User Service 수정):
```javascript
// user-service/server.js에서 응답 지연 추가
app.get('/users/:id', (req, res) => {
  setTimeout(() => {
    res.json({ id: req.params.id, name: 'John Doe', ... });
  }, 5000); // 5초 지연
});
```

**요청**:
```bash
curl -X GET "http://localhost:8080/api/dashboard/user-001" \
  -H "Authorization: Bearer <JWT_TOKEN>" | jq .
```

**예상 응답**:
```json
{
  "user": {},
  "orders": [...],
  "payments": [...],
  "summary": {
    "totalOrders": 1,
    "totalSpent": 59.98,
    "completedPayments": 1
  }
}
```

**검증 기준**:
- ✓ HTTP 200 OK
- ✓ user는 빈 객체 {}
- ✓ orders와 payments는 정상 반환
- ✓ 응답 시간 ≈ 3-4초 (User timeout 3초 + 나머지 병렬)

---

#### 테스트 4: Circuit Breaker 활성화

**테스트 목표**: Order Service가 연속으로 실패할 때 Circuit Breaker가 OPEN 상태로 전환되는지 확인

**시나리오**:
1. Order Service를 중지
2. 대시보드 API를 여러 번 호출 (minimumNumberOfCalls: 3)
3. Circuit Breaker가 OPEN 상태로 전환되면 빠르게 실패 응답

**요청** (3회 반복):
```bash
for i in {1..5}; do
  echo "=== Request $i ==="
  curl -X GET "http://localhost:8080/api/dashboard/user-001" \
    -H "Authorization: Bearer <JWT_TOKEN>" | jq '.orders'
  echo ""
  sleep 1
done
```

**예상 동작**:
- 처음 3-4회: 3초의 timeout 후 빈 orders 반환
- 5회차 이후: Circuit Breaker OPEN으로 인해 즉시 503 반환

**검증 기준**:
- ✓ 처음 요청들: 부분 실패 (orders = [])
- ✓ Circuit Breaker OPEN 후: HTTP 503 Service Unavailable
- ✓ 로그에 "Circuit breaker is OPEN" 메시지 나타남

**로그 확인**:
```bash
# BFF 로그에서 circuit breaker 상태 확인
tail -f bff-service.log | grep -i "circuit"
```

---

#### 테스트 5: Retry 메커니즘

**테스트 목표**: 일시적 오류가 발생했을 때 Retry로 복구되는지 확인

**시나리오**:
1. Order Service의 첫 응답을 실패하도록 설정
2. 재시도(Retry) 후 성공하는지 확인

**Mock Service 수정**:
```javascript
let callCount = 0;
app.get('/orders', (req, res) => {
  callCount++;
  if (callCount % 2 === 0) { // 짝수 번째는 실패
    res.status(500).json({ error: 'Internal Server Error' });
  } else { // 홀수 번째는 성공
    res.json([...orders]);
  }
});
```

**요청**:
```bash
curl -X GET "http://localhost:8080/api/dashboard/user-001" \
  -H "Authorization: Bearer <JWT_TOKEN>" | jq '.orders'
```

**예상 결과**:
- ✓ 첫 요청 실패 → Retry 수행
- ✓ Retry 성공 → orders 반환
- ✓ HTTP 200 OK

---

#### 테스트 6: JWT 인증 미검증

**테스트 목표**: 토큰 없이 요청할 때 401 Unauthorized 반환 확인

**요청** (토큰 없음):
```bash
curl -X GET "http://localhost:8080/api/dashboard/user-001"
```

**예상 응답**:
```
HTTP/1.1 401 Unauthorized
```

**검증 기준**:
- ✓ HTTP 401 Unauthorized
- ✓ JWT 인증 필터에서 차단

---

#### 테스트 7: Rate Limiting

**테스트 목표**: Rate Limit 초과 시 429 응답 확인

**요청** (1초 내에 101번 이상 요청):
```bash
for i in {1..150}; do
  curl -s -X GET "http://localhost:8080/api/dashboard/user-001" \
    -H "Authorization: Bearer <JWT_TOKEN>" \
    -w "Status: %{http_code}\n" >> /tmp/rate_limit_test.log
done
```

**예상 결과**:
- ✓ 처음 100개 요청: HTTP 200
- ✓ 101번째부터: HTTP 429 Too Many Requests

---

#### 테스트 8: 대사용자 수(동시성) 테스트

**테스트 목표**: 동시 요청 처리 확인

**도구**: Apache Bench 또는 wrk 사용

```bash
# Apache Bench (100개 요청, 10개 동시 연결)
ab -n 100 -c 10 \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  "http://localhost:8080/api/dashboard/user-001"
```

**예상 결과**:
- ✓ 모든 요청 처리 완료
- ✓ 평균 응답 시간 < 2초
- ✓ 실패 요청 없음 (또는 예상된 Circuit Breaker 오류만)

---

### 3.4 로그 분석

#### 성공 케이스 로그
```
[2025-12-19 15:47:47] INFO CompositionHandler - Fetching dashboard for userId: user-001
[2025-12-19 15:47:47] DEBUG CompositionHandler - Calling User Service for userId: user-001
[2025-12-19 15:47:47] DEBUG CompositionHandler - Calling Order Service for userId: user-001
[2025-12-19 15:47:47] DEBUG CompositionHandler - Calling Payment Service for userId: user-001
[2025-12-19 15:47:48] DEBUG CompositionHandler - User info retrieved: User{...}
[2025-12-19 15:47:48] DEBUG CompositionHandler - Orders retrieved: 1 items
[2025-12-19 15:47:48] DEBUG CompositionHandler - Payments retrieved: 1 items
```

#### 부분 실패 로그
```
[2025-12-19 15:47:47] INFO CompositionHandler - Fetching dashboard for userId: user-001
[2025-12-19 15:47:50] WARN CompositionHandler - Failed to fetch orders for userId: user-001, error: Connection refused
[2025-12-19 15:47:51] INFO CompositionHandler - Dashboard data combined successfully
```

#### Circuit Breaker 로그
```
[2025-12-19 15:47:47] ERROR CompositionHandler - Dashboard composition failed for userId: user-001
...io.github.resilience4j.circuitbreaker.CallNotPermittedException: CircuitBreaker 'dashboard' is OPEN...
```

## 4. Performance 측정

### 4.1 병렬 호출의 이점

**순차 호출 (Expected)**:
- User Service: 1초
- Order Service: 1초
- Payment Service: 1초
- **총 시간: 3초**

**병렬 호출 (Actual with Mono.zip)**:
- 모두 동시 실행
- **총 시간: 1초** (가장 느린 서비스 기준)

### 4.2 측정 방법

```bash
# cURL로 응답 시간 측정
time curl -X GET "http://localhost:8080/api/dashboard/user-001" \
  -H "Authorization: Bearer <JWT_TOKEN>" | jq .

# wrk를 사용한 성능 테스트
wrk -t4 -c100 -d30s \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  "http://localhost:8080/api/dashboard/user-001"
```

## 5. 트러블슈팅

### 5.1 "Circuit breaker is OPEN" 오류

**원인**: 일정 실패율 이상 시 Circuit Breaker가 OPEN 상태로 전환

**해결**:
```bash
# 1. 마이크로서비스 상태 확인
curl http://localhost:8081/health  # User Service
curl http://localhost:8082/health  # Order Service
curl http://localhost:8083/health  # Payment Service

# 2. 서비스 재시작
# 3. Circuit Breaker 상태 확인
curl http://localhost:8080/health | jq '.components.circuitBreakers'

# 4. waitDurationInOpenState 시간 대기 후 자동 복구
```

### 5.2 "Timeout waiting for response"

**원인**: 마이크로서비스가 3초 이상 응답하지 않음

**해결**:
```bash
# 1. 마이크로서비스 로그 확인
# 2. 데이터베이스 연결 상태 확인
# 3. 네트워크 연결성 테스트
ping <service-host>
```

### 5.3 "Connection refused"

**원인**: 마이크로서비스가 실행되지 않음

**해결**:
```bash
# 1. 마이크로서비스 프로세스 확인
lsof -i :8081  # User Service 포트
lsof -i :8082  # Order Service 포트
lsof -i :8083  # Payment Service 포트

# 2. 서비스 시작
# User Service 디렉토리에서: npm start
```

### 5.4 Redis 연결 오류

**원인**: Redis가 실행되지 않음

**해결**:
```bash
# Redis 상태 확인
redis-cli ping

# Redis 시작
redis-server

# 또는 Docker
docker ps | grep redis
docker run -d -p 6379:6379 redis:latest
```

## 6. 테스트 체크리스트

```
[ ] 1. 환경 준비
  [ ] Redis 실행 중 확인
  [ ] Mock 서비스 3개 실행 중 확인
  [ ] BFF 서비스 빌드 및 시작

[ ] 2. 기본 기능
  [ ] 로그인 성공
  [ ] JWT 토큰 발급
  [ ] 정상 대시보드 조회 (모든 서비스 정상)

[ ] 3. 부분 실패 처리
  [ ] User Service 다운 시 부분 성공
  [ ] Order Service 다운 시 부분 성공
  [ ] Payment Service 다운 시 부분 성공
  [ ] 타임아웃 처리 확인

[ ] 4. 복원력 메커니즘
  [ ] Circuit Breaker OPEN 상태 전환
  [ ] Circuit Breaker 자동 복구
  [ ] Retry 메커니즘 동작

[ ] 5. 보안
  [ ] 토큰 없이 요청 거부 (401)
  [ ] Rate Limiting 동작 (429)

[ ] 6. 성능
  [ ] 병렬 호출 시간 < 2초
  [ ] 동시성 테스트 성공 (100+ concurrent)

[ ] 7. 로깅
  [ ] DEBUG 로그 정상
  [ ] WARN 로그 정상 (부분 실패)
  [ ] ERROR 로그 정상 (Circuit Breaker)
```

## 7. 추가 리소스

### 관련 파일
- `bff-service/src/main/java/com/example/bff/handler/CompositionHandler.java`
- `bff-service/src/main/java/com/example/bff/config/RouteConfiguration.java`
- `bff-service/src/main/java/com/example/bff/dto/DashboardResponse.java`
- `bff-service/src/main/resources/application-local.yml`

### 참고 문서
- [Spring WebFlux Documentation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.html)
- [Project Reactor Mono/Flux](https://projectreactor.io/docs/core/latest/reference/)
- [Resilience4j Circuit Breaker](https://resilience4j.readme.io/docs/circuitbreaker)
