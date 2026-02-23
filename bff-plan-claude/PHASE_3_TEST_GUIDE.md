# Phase 3 Rate Limiting & Circuit Breaker 테스트 가이드

## 개요

Phase 3에서 구현한 Rate Limiting과 Circuit Breaker 기능을 테스트합니다.

**테스트할 기능:**
- Rate Limiting (사용자별 초당 요청 제한)
- Circuit Breaker (서비스 장애 격리)
- Retry (자동 재시도)
- Fallback (장애 응답)

---

## 테스트 환경 구성

### 필요한 서비스

1. **Redis** (Rate Limiting 상태 저장)
   ```bash
   docker run -d --name redis -p 6379:6379 redis:latest
   ```

2. **BFF Service** (로컬 프로파일)
   ```bash
   cd bff-service
   java -jar target/bff-service-1.0.0.jar --spring.profiles.active=local
   ```

3. **Mock 마이크로서비스**
   ```bash
   cd mock-services
   python3 user-service.py &
   python3 order-service.py &
   python3 payment-service.py &
   ```

---

## 테스트 시나리오

### 1. Rate Limiting 테스트

#### 1.1 정상 요청 (제한 이내)

로컬 환경에서는 초당 1000개 요청으로 설정되어 있으므로, 100개 미만의 요청으로 테스트합니다.

```bash
# 로그인하여 토큰 획득
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

echo "Token: $TOKEN"

# 정상 요청 (10개)
for i in {1..10}; do
  echo "Request $i:"
  curl -s -X GET http://localhost:8080/api/users/user-001 \
    -H "Authorization: Bearer $TOKEN" | jq '.id'
done
```

**예상 결과:** 모든 요청 성공 (200 OK)

---

#### 1.2 Rate Limit 초과 (429 Too Many Requests)

프로덕션 환경에서는 초당 100개로 설정되므로, 초과 요청을 시뮬레이션합니다.

```bash
# 로그인
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 빠른 요청 (로컬의 높은 제한을 초과하려면 많은 요청 필요)
# 또는 application-local.yml의 rate-limit.requests-per-second를 낮게 설정하고 테스트

# Redis에서 rate limit 키 확인
docker exec -it redis redis-cli
> KEYS "rate_limit:*"
> GET "rate_limit:user-001"
```

**예상 결과:**
- Redis에 `rate_limit:user-001` 키 존재
- TTL 1초 설정됨
- 초과 시 429 응답

---

### 2. Circuit Breaker 테스트

#### 2.1 정상 서비스 호출

```bash
# 로그인
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 정상 요청
curl -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN" | jq
```

**예상 결과:** 200 OK, Mock User Service 응답

---

#### 2.2 서비스 장애 시뮬레이션 (Circuit Breaker OPEN)

Mock 서비스를 중지하여 장애 상황을 시뮬레이션합니다.

```bash
# 1. Mock User Service 중지
ps aux | grep user-service.py
kill <PID>

# 2. BFF에서 User Service 호출 (실패 예상)
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 여러 번 요청하여 Circuit Breaker 트리거
for i in {1..5}; do
  echo "Request $i:"
  curl -s -X GET http://localhost:8080/api/users/user-001 \
    -H "Authorization: Bearer $TOKEN"
  echo ""
done

# 3. Circuit Breaker 상태 확인
# 로그에서 "Circuit breaker is OPEN" 메시지 확인
```

**예상 결과:**
- 초기 요청: 503 Service Unavailable (Connection refused)
- 재시도 2회 시도
- 실패율 50% 이상 도달 시 Circuit Breaker OPEN
- Circuit Breaker OPEN 상태: 즉시 503 응답 (빠른 실패)

---

#### 2.3 Circuit Breaker Half-Open (회복 시도)

Circuit Breaker가 OPEN 상태에서 일정 시간 경과 후 Half-Open 상태로 전환됩니다.

```bash
# 1. 서비스 재시작
cd mock-services
python3 user-service.py &

# 2. Circuit Breaker Half-Open 상태에서 재시도
# 로컬 환경: waitDurationInOpenState: 5000ms (5초)
echo "Waiting for Circuit Breaker to transition to Half-Open..."
sleep 6

TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 테스트 요청
curl -s -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN" | jq
```

**예상 결과:**
- 5초 후 Circuit Breaker Half-Open으로 전환
- Half-Open 상태에서 제한된 요청(3개) 허용
- 성공 시 Circuit Breaker CLOSED로 복구
- 실패 시 다시 OPEN으로 전환

---

### 3. Retry 테스트

#### 3.1 일시적 오류 재시도

일시적인 네트워크 오류 또는 타임아웃 상황에서 자동 재시도됩니다.

```bash
# 로그인
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 요청 (로그에서 재시도 로그 확인)
curl -v -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN" 2>&1 | grep "Retry"
```

**예상 결과:**
- 로컬 환경: 최대 2회 재시도 (maxAttempts: 2)
- 프로덕션: 최대 3회 재시도
- 재시도 간격: 지수 백오프 (500ms, 1000ms)
- 로그에 "Retrying..." 메시지 출력

---

### 4. Rate Limiting + Circuit Breaker 통합 테스트

#### 4.1 Rate Limit 먼저 적용되는 경우

```bash
# 로그인
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# Rate Limit 설정을 낮게 변경 (테스트용)
# application-local.yml: rate-limit.requests-per-second: 5로 설정 후 재시작

# 빠른 연속 요청
for i in {1..10}; do
  echo "Request $i:"
  curl -s -X GET http://localhost:8080/api/users/user-001 \
    -H "Authorization: Bearer $TOKEN"
  echo ""
done
```

**예상 결과:**
- 요청 1-5: 200 OK
- 요청 6-10: 429 Too Many Requests (Rate Limit)

---

### 5. 공개 엔드포인트 Rate Limiting 제외

공개 엔드포인트는 Rate Limiting이 적용되지 않습니다.

```bash
# 로그인은 Rate Limiting 제외
for i in {1..50}; do
  curl -s -X POST http://localhost:8080/login \
    -H "Content-Type: application/json" \
    -d '{"username":"admin","password":"password123"}' > /dev/null
  echo "Login $i: OK"
done
```

**예상 결과:** 모든 로그인 요청 성공 (429 없음)

---

## 테스트 자동화 스크립트

### Bash 스크립트

```bash
#!/bin/bash

set -e

echo "=================================================="
echo "Phase 3 Rate Limiting & Circuit Breaker 테스트"
echo "=================================================="
echo ""

# 1. 서버 상태 확인
echo "1. 서버 상태 확인"
curl -s http://localhost:8080/health | jq
echo ""

# 2. 로그인
echo "2. 로그인 테스트"
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}')

TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.accessToken')
echo "Token obtained: ${TOKEN:0:20}..."
echo ""

# 3. Rate Limiting 테스트
echo "3. Rate Limiting 테스트 (정상 요청)"
RATE_LIMIT_PASSED=0
for i in {1..5}; do
  RESPONSE=$(curl -s -w "\n%{http_code}" -X GET http://localhost:8080/api/users/user-001 \
    -H "Authorization: Bearer $TOKEN")
  STATUS=$(echo "$RESPONSE" | tail -1)
  echo "Request $i: HTTP $STATUS"
  if [ "$STATUS" = "200" ]; then
    ((RATE_LIMIT_PASSED++))
  fi
done
echo "Rate Limiting Test: $RATE_LIMIT_PASSED/5 passed"
echo ""

# 4. Redis Rate Limit 확인
echo "4. Redis Rate Limit 확인"
docker exec redis redis-cli KEYS "rate_limit:*" || echo "Redis not accessible"
echo ""

# 5. Circuit Breaker 정보 (로그에서 확인)
echo "5. 응용 로그 확인"
echo "BFF Service 로그를 확인하세요:"
echo "- 'Circuit breaker is OPEN' 메시지"
echo "- 'Retrying' 메시지"
echo "- 'Rate limit exceeded' 메시지"
echo ""

# 6. Health Check
echo "6. Health Check (Circuit Breaker 상태 포함)"
curl -s http://localhost:8080/actuator/health | jq

echo ""
echo "=================================================="
echo "테스트 완료"
echo "=================================================="
```

---

## Redis 데이터 확인

### Rate Limiting 상태 확인

```bash
# Redis CLI 접속
docker exec -it redis redis-cli

# Rate Limit 키 조회
KEYS "rate_limit:*"

# 특정 사용자의 Rate Limit 카운트 확인
GET "rate_limit:user-001"

# TTL 확인 (1초로 설정)
TTL "rate_limit:user-001"
```

---

## 주요 테스트 케이스

| 케이스 | 요청 | 예상 결과 |
|--------|------|---------|
| **정상 요청** | GET /api/users/user-001 + Bearer Token | 200, 프록시 응답 |
| **Rate Limit 초과** | 초당 제한(100)을 초과한 요청 | 429 Too Many Requests |
| **공개 엔드포인트** | POST /login (여러 번) | 모두 200 (Rate Limit 미적용) |
| **서비스 정상** | GET /api/users/** (서비스 실행 중) | 200, Circuit Breaker CLOSED |
| **서비스 장애** | GET /api/users/** (서비스 중지) | 503 후 Circuit Breaker OPEN |
| **Circuit Breaker Half-Open** | 5초 대기 후 요청 | 200 (또는 실패 시 OPEN으로 복귀) |
| **Retry** | 일시적 오류 | 2~3회 재시도 후 성공 또는 실패 |
| **토큰 없이 API 호출** | GET /api/users/** (토큰 없음) | 401 Unauthorized |

---

## 성능 지표

### Circuit Breaker 설정 (로컬)

```yaml
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        slidingWindowSize: 10          # 최근 10개 호출 평가
        minimumNumberOfCalls: 3        # 최소 3개 호출 필요
        failureRateThreshold: 50       # 50% 이상 실패 시 OPEN
        waitDurationInOpenState: 5000ms # 5초 후 Half-Open

  retry:
    instances:
      user-service:
        maxAttempts: 2                 # 최대 2회 재시도
        waitDuration: 500ms            # 첫 재시도 500ms
        exponentialBackoffMultiplier: 2 # 지수 백오프
```

### Rate Limiting 설정 (로컬)

```yaml
rate-limit:
  enabled: true
  requests-per-second: 1000  # 테스트용 높은 제한
```

---

## 문제 해결

### Circuit Breaker가 작동하지 않음

**원인:** 최소 호출 수(3)에 도달하지 못함

**해결:**
```bash
# 최소 호출 수 이상으로 요청
for i in {1..5}; do
  curl -s http://localhost:8080/api/users/user-001 -H "Authorization: Bearer $TOKEN"
done
```

---

### Rate Limit이 작동하지 않음

**원인:** Redis 연결 실패 또는 rate-limit.enabled 비활성화

**해결:**
```bash
# Redis 상태 확인
docker ps | grep redis

# Redis 재시작
docker run -d --name redis -p 6379:6379 redis:latest

# Rate Limiting 활성화 확인 (application-local.yml)
rate-limit:
  enabled: true
```

---

### Rate Limit이 너무 엄격함

**원인:** 로컬 환경에서 제한이 낮게 설정됨

**해결:**
```yaml
# application-local.yml 수정
rate-limit:
  requests-per-second: 1000  # 테스트용으로 높게 설정
```

---

## 다음 단계

Phase 3 테스트가 완료되었으면 다음을 진행할 수 있습니다:

- **Phase 4**: API Composition
  - 여러 서비스 데이터 조합
  - 병렬 처리
  - 부분 실패 처리

- **Phase 5**: 모니터링 & 분산 트레이싱
  - Micrometer Tracing
  - Zipkin 연동
  - Prometheus 메트릭

- **Phase 6**: Kubernetes 배포
  - Dockerfile 최적화
  - K8s 매니페스트 작성
  - 헬스 체크 설정

---

## 로그 분석 팁

### Circuit Breaker 로그

```
Circuit breaker user-service is now OPEN
Waiting 5000ms before transitioning to HALF_OPEN
Circuit breaker user-service is now HALF_OPEN
Circuit breaker user-service is now CLOSED
```

### Rate Limiting 로그

```
Rate limit exceeded for userId: user-001 (count: 101)
```

### Retry 로그

```
Retrying request (attempt 1/2) after 500ms
Retrying request (attempt 2/2) after 1000ms
```

---
