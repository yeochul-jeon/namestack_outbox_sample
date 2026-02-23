# Phase 3 Quick Reference Guide

## Rate Limiting & Circuit Breaker 빠른 참조

---

## Rate Limiting

### Redis에서 Rate Limit 확인

```bash
# Redis CLI 접속
docker exec -it bff-redis redis-cli

# Rate limit 키 조회
KEYS "rate_limit:*"

# 특정 사용자의 카운트 확인
GET "rate_limit:user-001"

# TTL 확인
TTL "rate_limit:user-001"

# 모든 rate limit 데이터 삭제 (테스트용)
FLUSHALL
```

### Rate Limit 설정 변경

```yaml
# application-local.yml에서 수정
rate-limit:
  enabled: true
  requests-per-second: 1000  # 로컬 (높음)
```

```yaml
# application.yml에서 수정
rate-limit:
  enabled: true
  requests-per-second: 100   # 프로덕션 (낮음)
```

---

## Circuit Breaker 상태 확인

### 로그 출력

```bash
# BFF 서비스 로그 모니터링
docker logs -f bff-service

# Circuit Breaker 상태 메시지 찾기
# "Circuit breaker user-service is now OPEN"
# "Circuit breaker user-service is now HALF_OPEN"
# "Circuit breaker user-service is now CLOSED"
```

### Health Check 엔드포인트

```bash
# Circuit Breaker 상태 확인
curl -s http://localhost:8080/actuator/health | jq '.components.circuitBreakerHealthIndicator'
```

---

## 서비스 장애 테스트

### 장애 시뮬레이션

```bash
# 1. Mock User Service 중지
docker stop mock-user-service

# 2. BFF에서 User Service 호출 (실패 예상)
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

curl -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN"

# 3. 여러 번 호출하여 Circuit Breaker 트리거
for i in {1..5}; do
  curl -s -X GET http://localhost:8080/api/users/user-001 \
    -H "Authorization: Bearer $TOKEN"
  echo "Request $i completed"
done

# 4. Circuit Breaker OPEN 상태 확인
# 즉시 503 응답을 받아야 함 (재시도하지 않음)
```

### 서비스 복구

```bash
# 1. Mock User Service 재시작
docker start mock-user-service

# 2. Circuit Breaker Half-Open 대기 (5초 로컬)
sleep 6

# 3. Half-Open 상태에서 재시도
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

curl -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN"

# 4. 성공하면 Circuit Breaker CLOSED로 전환
```

---

## Retry 메커니즘

### Retry 확인

```bash
# 로그에서 "Retrying" 메시지 확인
docker logs -f bff-service | grep -i retry

# 예상 출력:
# Retrying request (attempt 1/2) after 500ms
# Retrying request (attempt 2/2) after 1000ms
```

### Retry 설정 (로컬)

```yaml
retry:
  instances:
    user-service:
      maxAttempts: 2              # 최대 2회 재시도
      waitDuration: 500ms         # 첫 재시도: 500ms
      enableExponentialBackoff: true
      exponentialBackoffMultiplier: 2  # 두 번째: 1000ms
```

---

## 설정 리소스

### Circuit Breaker 설정 예제

```yaml
resilience4j:
  circuitbreaker:
    instances:
      user-service:
        slidingWindowSize: 10              # 평가할 최근 호출 수
        minimumNumberOfCalls: 5            # 회로 개방에 필요한 최소 호출 수
        failureRateThreshold: 50           # 실패율 임계값 (%)
        slowCallRateThreshold: 50          # 느린 호출 임계값 (%)
        slowCallDurationThreshold: 2000ms  # 느린 호출 판단 시간
        waitDurationInOpenState: 10000ms   # Open 상태 유지 시간
        permittedNumberOfCallsInHalfOpenState: 3  # Half-Open에서 허용 호출 수
        automaticTransitionFromOpenToHalfOpenEnabled: true
```

### Rate Limit 설정 예제

```yaml
rate-limit:
  enabled: true              # Rate Limiting 활성화
  requests-per-second: 100   # 초당 요청 제한
```

---

## 공개 엔드포인트 (Rate Limiting 제외)

Rate Limiting이 적용되지 않는 엔드포인트:
- `/health` - 헬스 체크
- `/ready` - 준비 상태
- `/login` - 로그인
- `/refresh` - 토큰 갱신
- `/docs/**` - API 문서
- `/swagger/**` - Swagger UI
- `/actuator/**` - 액추에이터

---

## 성능 최적화

### Rate Limit 설정 가이드

```yaml
# 개발 환경
requests-per-second: 1000   # 높음 (테스트 자유)

# 스테이징
requests-per-second: 500    # 중간

# 프로덕션
requests-per-second: 100    # 낮음 (보안)
```

### Circuit Breaker 설정 가이드

```yaml
# 개발 환경
minimumNumberOfCalls: 3          # 빠른 트리거
waitDurationInOpenState: 5000ms  # 빠른 복구

# 프로덕션
minimumNumberOfCalls: 10         # 천천한 트리거
waitDurationInOpenState: 60000ms # 느린 복구
```

---

## 자동 테스트

### Quick Test 실행

```bash
# 기본 테스트 실행
./test-phase3.sh

# 상세 테스트 가이드 확인
cat PHASE_3_TEST_GUIDE.md
```

### 수동 테스트

```bash
# 1. 로그인
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 2. API 호출
curl -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN" | jq

# 3. Redis 데이터 확인
docker exec bff-redis redis-cli KEYS "rate_limit:*"
```

---

## 에러 응답 코드

| 코드 | 의미 | 원인 |
|------|------|------|
| 200 | OK | 정상 응답 |
| 401 | Unauthorized | 토큰 없음 또는 만료 |
| 429 | Too Many Requests | Rate Limit 초과 |
| 503 | Service Unavailable | Circuit Breaker OPEN 또는 재시도 실패 |

---

## 문제 해결

### Q: Rate Limit이 작동하지 않음
A:
```bash
# Redis 연결 확인
docker exec bff-redis redis-cli ping

# Rate Limiting 활성화 확인
grep "rate-limit" application-local.yml
```

### Q: Circuit Breaker가 OPEN되지 않음
A:
```bash
# 로그에서 실패 메시지 확인
docker logs bff-service | grep -i "circuit"

# 최소 호출 수 확인 (최소 3-5회)
for i in {1..5}; do
  curl -X GET http://localhost:8080/api/users/user-001 \
    -H "Authorization: Bearer $TOKEN"
done
```

### Q: Retry가 실행되지 않음
A:
```bash
# 로그에서 재시도 메시지 확인
docker logs bff-service | grep -i "retry"

# 재시도 가능한 예외 확인 (IOException, SocketException)
```

---

## 모니터링 대시보드

### Rate Limit 모니터링

```bash
# 실시간 Rate Limit 카운트 확인
watch "docker exec bff-redis redis-cli KEYS 'rate_limit:*' | wc -l"
```

### Circuit Breaker 상태 모니터링

```bash
# 실시간 로그 모니터링
docker logs -f bff-service | grep -E "(OPEN|CLOSED|HALF_OPEN|Retrying)"
```

---

## Docker Compose 명령어

```bash
# 모든 서비스 시작
docker-compose up -d

# 로그 확인
docker-compose logs -f bff-service

# Redis CLI 접속
docker-compose exec redis redis-cli

# 특정 서비스 중지 (장애 테스트)
docker-compose stop user-service

# 특정 서비스 재시작
docker-compose start user-service

# 모든 컨테이너 중지
docker-compose down

# 데이터 초기화
docker-compose down -v
```

---

## 기타 유용한 명령어

```bash
# BFF 서비스 JAR 파일 크기
ls -lh bff-service/target/bff-service-1.0.0.jar

# 프로젝트 빌드
mvn clean package -DskipTests

# 컨테이너 네트워크 확인
docker network ls

# 컨테이너 간 통신 확인
docker exec bff-service ping -c 1 user-service
```

---

## 참고 문서

- **PHASE_3_TEST_GUIDE.md** - 상세 테스트 가이드
- **PHASE_3_IMPLEMENTATION_SUMMARY.md** - 구현 상세 내용
- **pom.xml** - 의존성 목록
- **application.yml** - 프로덕션 설정
- **application-local.yml** - 로컬 개발 설정

---

## 체크리스트

Phase 3 완성도 확인:

- [x] RateLimitingFilter 구현
- [x] Circuit Breaker 설정
- [x] Retry 메커니즘
- [x] Redis 통합
- [x] Docker Compose 업데이트
- [x] 테스트 가이드 작성
- [x] 자동 테스트 스크립트
- [x] 모니터링 가이드

---

**Phase 3 완료! 🎉**

다음: Phase 4 API Composition
