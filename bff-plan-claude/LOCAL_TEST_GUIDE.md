# Phase 1 로컬 테스트 가이드

## 개요

BFF 서비스를 로컬에서 테스트하기 위한 두 가지 방법을 제공합니다:

1. **Docker Compose를 사용한 통합 테스트** (권장)
2. **수동 테스트** (개발 중)

---

## 방법 1: Docker Compose를 사용한 테스트 (권장)

### 요구사항
- Docker & Docker Compose 설치

### 실행 단계

```bash
# 1. 프로젝트 루트로 이동
cd /Users/cjenm/project/bff-plan-claude

# 2. Docker Compose로 모든 서비스 시작
docker-compose up -d

# 3. 서비스 상태 확인
docker-compose ps

# 4. 로그 확인
docker-compose logs -f bff-service
```

### 서비스 포트
- **BFF Service**: http://localhost:8080
- **User Service**: http://localhost:8081
- **Order Service**: http://localhost:8082
- **Payment Service**: http://localhost:8083

### 테스트 API 호출

#### User API
```bash
# 모든 사용자 조회
curl -s http://localhost:8080/api/users | jq

# 특정 사용자 조회
curl -s http://localhost:8080/api/users/user-001 | jq

# 사용자 생성
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"New User","email":"new@example.com","role":"user"}' | jq
```

#### Order API
```bash
# 모든 주문 조회
curl -s http://localhost:8080/api/orders | jq

# 특정 사용자의 주문 조회
curl -s http://localhost:8080/api/orders?userId=user-001 | jq

# 주문 생성
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":"user-001","items":[{"productId":"prod-001","quantity":1,"price":29.99}],"total":29.99}' | jq
```

#### Payment API
```bash
# 모든 결제 조회
curl -s http://localhost:8080/api/payments | jq

# 특정 사용자의 결제 조회
curl -s http://localhost:8080/api/payments?userId=user-001 | jq
```

#### 헬스 체크
```bash
# BFF Service 헬스 체크
curl -s http://localhost:8080/health | jq

# User Service 헬스 체크
curl -s http://localhost:8081/health | jq

# Order Service 헬스 체크
curl -s http://localhost:8082/health | jq

# Payment Service 헬스 체크
curl -s http://localhost:8083/health | jq
```

### 서비스 종료

```bash
# 모든 서비스 중지
docker-compose down

# 볼륨까지 제거
docker-compose down -v

# 로그 확인
docker-compose logs
```

---

## 방법 2: 수동 테스트 (개발 중)

### BFF 서비스만 시작

```bash
cd bff-service

# JAR 빌드
mvn clean package -DskipTests

# 로컬 프로필로 실행
java -jar target/bff-service-1.0.0.jar --spring.profiles.active=local
```

### Mock 서비스 별도 시작

각 Mock 서비스를 별도의 터미널에서 시작합니다.

**터미널 1 - User Service:**
```bash
cd mock-services
python3 user-service.py
```

**터미널 2 - Order Service:**
```bash
cd mock-services
python3 order-service.py
```

**터미널 3 - Payment Service:**
```bash
cd mock-services
python3 payment-service.py
```

---

## 테스트 시나리오

### 시나리오 1: 사용자 조회 및 대시보드 (Phase 4에서 구현)

```bash
# 1. BFF를 통해 User Service 호출
curl -s http://localhost:8080/api/users/user-001 | jq

# 2. 해당 사용자의 주문 조회
curl -s http://localhost:8080/api/orders?userId=user-001 | jq

# 3. 해당 사용자의 결제 조회
curl -s http://localhost:8080/api/payments?userId=user-001 | jq
```

### 시나리오 2: HTTP 메서드 테스트

```bash
# GET 테스트
curl -X GET http://localhost:8080/api/users | jq

# POST 테스트 (사용자 생성)
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","email":"test@example.com"}' | jq

# PUT 테스트 (사용자 수정) - user-001
curl -X PUT http://localhost:8080/api/users/user-001 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Name"}' | jq

# DELETE 테스트 (사용자 삭제) - 새로 생성한 사용자 사용
curl -X DELETE http://localhost:8080/api/users/user-999 | jq
```

---

## 문제 해결

### BFF Service가 Mock Service에 연결할 수 없음

**원인**: 환경 설정에서 서비스 URL이 잘못되었을 가능성

**해결**:
- Docker Compose 사용: `docker-compose.yml`에서 서비스명 확인
- 로컬 테스트: `application-local.yml`에서 localhost URL 확인

### 포트 충돌

```bash
# 특정 포트 사용 중인 프로세스 확인
lsof -i :8080
lsof -i :8081
lsof -i :8082
lsof -i :8083

# 프로세스 종료
kill -9 <PID>
```

### Docker 이미지 빌드 실패

```bash
# Maven 캐시 삭제
mvn clean -DskipTests

# Docker 이미지 재빌드
docker-compose build --no-cache

# Docker 이미지 확인
docker images | grep bff
```

---

## 예상 응답

### 헬스 체크 (성공)
```json
{
  "status": "UP",
  "service": "bff-service"
}
```

### User API - GET /api/users (성공)
```json
[
  {
    "id": "user-001",
    "name": "John Doe",
    "email": "john@example.com",
    "role": "admin",
    "created_at": "2023-01-01T00:00:00Z"
  },
  ...
]
```

### User API - GET /api/users/{id} (성공)
```json
{
  "id": "user-001",
  "name": "John Doe",
  "email": "john@example.com",
  "role": "admin",
  "created_at": "2023-01-01T00:00:00Z"
}
```

### Error Response (사용자 없음)
```json
{
  "error": "User not found"
}
```

---

## 다음 단계

테스트가 완료되면 다음 Phase를 진행할 수 있습니다:

- **Phase 2**: JWT 인증 추가
  - 로그인 엔드포인트
  - JWT 토큰 검증 필터
  - Redis 기반 Refresh Token

- **Phase 3**: Rate Limiting & Circuit Breaker
  - 요청 제한
  - 장애 전파 방지

- **Phase 4**: API Composition
  - Dashboard API (여러 서비스 데이터 조합)
  - 병렬 처리

---

## 참고사항

- Mock 서비스는 실제 데이터 저장소가 없으므로 재시작되면 데이터가 초기화됩니다.
- 더 복잡한 테스트는 Phase 5에서 모니터링 및 로깅 설정 후 진행하세요.
- K8s 배포 테스트는 Phase 6에서 Minikube를 사용하여 수행할 수 있습니다.
