# Phase 4: API Composition - 빠른 시작 가이드

## 완료된 작업

✅ **4개 DTO 모델 생성**
- User.java
- Order.java  
- Payment.java
- DashboardResponse.java (with DashboardSummary)

✅ **CompositionHandler 구현**
- 여러 마이크로서비스 병렬 호출 (Mono.zip)
- 부분 실패 처리 (Graceful Degradation)
- Circuit Breaker + Retry 적용

✅ **RouteConfiguration 업데이트**
- GET /api/dashboard/{userId} 엔드포인트 추가

✅ **Resilience4j 설정**
- application.yml, application-local.yml에 dashboard 설정 추가

✅ **프로젝트 빌드 성공**
- mvn clean package 완료

✅ **테스트 문서 작성**
- PHASE_4_TEST_GUIDE.md - 상세 테스트 가이드
- test-phase4.sh - 자동 테스트 스크립트

---

## 빨리 테스트해보기

### 1단계: 환경 준비

```bash
# Redis 실행 (포트 6379)
redis-server

# 또는 Docker 사용
docker run -d -p 6379:6379 redis:latest
```

### 2단계: Mock 마이크로서비스 시작

각각 다른 터미널에서:

```bash
# 터미널 1: User Service (포트 8081)
cd /mock/user-service && npm start

# 터미널 2: Order Service (포트 8082)
cd /mock/order-service && npm start

# 터미널 3: Payment Service (포트 8083)
cd /mock/payment-service && npm start
```

### 3단계: BFF 서비스 시작

```bash
# 터미널 4
cd /Users/cjenm/project/bff-plan-claude/bff-service
java -jar target/bff-service-1.0.0.jar --spring.profiles.active=local

# 또는
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

### 4단계: 로그인 및 대시보드 조회

```bash
# 1. 로그인
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john","password":"password123"}' | jq .

# 응답에서 userId 확인: "user-001"

# 2. 대시보드 조회
curl -X GET "http://localhost:8080/api/dashboard/user-001" \
  -H "Authorization: Bearer user-001" | jq .
```

### 5단계: 자동 테스트 실행

```bash
cd /Users/cjenm/project/bff-plan-claude
chmod +x test-phase4.sh
./test-phase4.sh
```

---

## 주요 엔드포인트

| 메서드 | 경로 | 설명 | 인증 |
|--------|------|------|------|
| POST | /login | 로그인 | ✗ |
| GET | /api/dashboard/{userId} | 대시보드 조회 | ✓ |
| GET | /health | 헬스 체크 | ✗ |
| GET | /ready | Readiness 확인 | ✗ |

---

## 대시보드 응답 예시

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
      "items": [...],
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

---

## 성능 특성

- **응답 시간**: ~1초 (병렬 호출)
- **각 서비스 타임아웃**: 3초
- **Circuit Breaker**: 실패율 50% 이상 시 OPEN
- **Retry**: 최대 2회 시도 (500ms 간격)

---

## 문제 해결

### "Circuit breaker is OPEN" 오류
→ 마이크로서비스 상태 확인 및 10초 대기

### "Connection refused"
→ Mock 마이크로서비스 실행 확인

### "Timeout waiting for response"
→ 마이크로서비스 성능 확인 (로그 확인)

---

## 상세 문서

- **PHASE_4_TEST_GUIDE.md** - 완전한 테스트 가이드
- **PHASE_4_IMPLEMENTATION_SUMMARY.md** - 기술 상세 설명
- **test-phase4.sh** - 자동화된 테스트 스크립트

---

## 주요 파일

```
bff-service/src/main/java/com/example/bff/
├── handler/CompositionHandler.java ← API 조합 로직
├── dto/
│   ├── User.java
│   ├── Order.java
│   ├── Payment.java
│   └── DashboardResponse.java
└── config/RouteConfiguration.java ← 라우팅 설정 (수정됨)

src/main/resources/
├── application.yml (수정됨)
└── application-local.yml (수정됨)
```

---

## 다음 단계

Phase 5 구현 준비:
- Micrometer Tracing 설정
- Zipkin 연동
- Prometheus 메트릭 수집
- 성능 모니터링 대시보드

---

구현 완료일: 2025-12-19
상태: ✅ 완료 및 테스트 준비 완료
