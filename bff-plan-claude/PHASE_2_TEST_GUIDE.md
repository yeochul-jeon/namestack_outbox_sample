# Phase 2 JWT 인증 테스트 가이드

## 개요

Phase 2에서 구현한 JWT 기반 인증/인가 기능을 테스트합니다.

**테스트할 기능:**
- 로그인 (JWT 토큰 발급)
- 토큰을 사용한 API 호출
- 토큰 갱신 (Refresh Token)
- 로그아웃
- 토큰 만료 테스트

---

## 테스트 환경 구성

### 필요한 서비스

1. **Redis** (토큰 저장소)
   ```bash
   # Docker로 실행
   docker run -d --name redis -p 6379:6379 redis:latest
   ```

2. **BFF Service**
   ```bash
   cd bff-service
   java -jar target/bff-service-1.0.0.jar --spring.profiles.active=local
   ```

3. **Mock 마이크로서비스** (선택사항)
   ```bash
   cd mock-services
   python3 user-service.py &
   python3 order-service.py &
   python3 payment-service.py &
   ```

---

## 테스트 시나리오

### 1. 로그인 테스트

#### 1.1 유효한 자격증명으로 로그인

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password123"
  }' | jq
```

**예상 응답:**
```json
{
  "userId": "user-001",
  "username": "admin",
  "roles": ["ADMIN", "USER"],
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": 3600
}
```

#### 1.2 잘못된 자격증명으로 로그인

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "wrongpassword"
  }' | jq
```

**예상 응답:**
```json
{
  "error": "Authentication failed",
  "message": "Invalid credentials"
}
```

---

### 2. 인증된 API 호출 테스트

#### 2.1 토큰 없이 보호된 API 호출

```bash
curl -X GET http://localhost:8080/api/users/user-001 | jq
```

**예상 응답:** `401 Unauthorized`

#### 2.2 Authorization 헤더로 토큰 전달

```bash
# 로그인하여 accessToken 획득
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

echo "Token: $TOKEN"

# 헤더로 토큰 전달
curl -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN" | jq
```

**예상 응답:** Mock User Service의 응답 (또는 프록시 성공)

#### 2.3 쿠키로 토큰 전달

```bash
# 로그인 (쿠키 저장)
curl -c cookies.txt -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq

# 쿠키를 사용하여 보호된 API 호출
curl -b cookies.txt -X GET http://localhost:8080/api/users/user-001 | jq
```

---

### 3. 토큰 갱신 테스트

```bash
# 1. 로그인
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}')

REFRESH_TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.refreshToken')
echo "Refresh Token: $REFRESH_TOKEN"

# 2. 토큰 갱신
curl -X POST http://localhost:8080/refresh \
  -H "Content-Type: application/json" \
  -d "{\"refreshToken\": \"$REFRESH_TOKEN\"}" | jq
```

**예상 응답:**
```json
{
  "userId": "user-001",
  "username": "admin",
  "roles": ["ADMIN", "USER"],
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9...",
  "expiresIn": 3600
}
```

---

### 4. 토큰 검증 테스트

#### 4.1 만료된 토큰으로 API 호출

토큰의 만료 시간을 조정하거나 기다려서 테스트합니다.

```bash
# JWT 토큰 디코딩 (토큰의 내용 확인)
# 온라인 도구: https://jwt.io
curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken'
```

#### 4.2 변조된 토큰으로 API 호출

```bash
# 토큰을 일부 수정
MODIFIED_TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyLTAwMSIsImV4cCI6MTcwMjk5OTk5OX0.invalid"

curl -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $MODIFIED_TOKEN" | jq
```

**예상 응답:** `401 Unauthorized`

---

### 5. 로그아웃 테스트

```bash
# 로그인
TOKEN=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}' | jq -r '.accessToken')

# 로그아웃
curl -X POST http://localhost:8080/logout \
  -H "Authorization: Bearer $TOKEN" | jq
```

**예상 응답:**
```json
{
  "message": "Logout successful",
  "userId": "user-001"
}
```

---

## 테스트 자동화 스크립트

### Bash 스크립트

```bash
#!/bin/bash

echo "=================================================="
echo "Phase 2 JWT 인증 테스트"
echo "=================================================="
echo ""

# 서버 상태 확인
echo "1. 서버 상태 확인"
curl -s http://localhost:8080/health | jq
echo ""

# 로그인
echo "2. 로그인 테스트"
LOGIN_RESPONSE=$(curl -s -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}')

echo $LOGIN_RESPONSE | jq
TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.accessToken')
REFRESH_TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.refreshToken')

echo ""
echo "3. 토큰으로 보호된 API 호출"
curl -s -X GET http://localhost:8080/api/users/user-001 \
  -H "Authorization: Bearer $TOKEN" | jq

echo ""
echo "4. 토큰 갱신"
curl -s -X POST http://localhost:8080/refresh \
  -H "Content-Type: application/json" \
  -d "{\"refreshToken\": \"$REFRESH_TOKEN\"}" | jq

echo ""
echo "5. 로그아웃"
curl -s -X POST http://localhost:8080/logout \
  -H "Authorization: Bearer $TOKEN" | jq

echo ""
echo "=================================================="
echo "테스트 완료"
echo "=================================================="
```

---

## 주요 테스트 케이스

| 케이스 | 요청 | 예상 결과 |
|--------|------|---------|
| **로그인 성공** | POST /login (유효한 자격증명) | 200, JWT 토큰 반환 |
| **로그인 실패** | POST /login (잘못된 자격증명) | 401 Unauthorized |
| **인증 없이 API 호출** | GET /api/users/** | 401 Unauthorized |
| **유효한 토큰으로 API 호출** | GET /api/users/** + Bearer Token | 200, 프록시 응답 |
| **잘못된 토큰으로 API 호출** | GET /api/users/** + 유효하지 않은 Token | 401 Unauthorized |
| **토큰 갱신** | POST /refresh + refreshToken | 200, 새로운 토큰 반환 |
| **로그아웃** | POST /logout + Bearer Token | 200, 로그아웃 성공 |

---

## Redis 데이터 확인

### Redis에 저장된 Refresh Token 확인

```bash
# Redis CLI 접속
docker exec -it redis redis-cli

# 저장된 Refresh Token 조회
KEYS "refresh_token:*"

# 특정 사용자의 Refresh Token 확인
GET "refresh_token:user-001"

# TTL 확인 (7일)
TTL "refresh_token:user-001"
```

---

## 테스트 결과 정리

### 성공 기준
- ✅ 유효한 자격증명으로 로그인 성공
- ✅ JWT 토큰 발급 확인
- ✅ AccessToken으로 API 호출 성공
- ✅ RefreshToken으로 토큰 갱신 성공
- ✅ 잘못된 토큰으로 API 호출 실패 (401)
- ✅ Redis에 RefreshToken 저장 확인

---

## 다음 단계

Phase 2 테스트가 완료되었으면 다음을 진행할 수 있습니다:

- **Phase 3**: Rate Limiting & Circuit Breaker
  - 요청 빈도 제한
  - 장애 격리

- **Phase 4**: API Composition
  - 여러 서비스 데이터 조합
  - 병렬 처리

---

## 문제 해결

### Redis 연결 실패

```
Error: Connection refused
```

**해결:**
```bash
# Redis 상태 확인
docker ps | grep redis

# Redis 시작
docker run -d --name redis -p 6379:6379 redis:latest
```

### 토큰 검증 실패

```
Invalid JWT token
```

**해결:**
- JWT Secret이 설정과 일치하는지 확인
- 토큰 형식 확인 (Bearer 포함)
- 토큰 만료 시간 확인

### CORS 오류

로컬 테스트에서 브라우저 기반 테스트 시 CORS 오류 발생:

**해결:**
- curl/Postman 등 CLI 도구로 테스트
- Spring Security CORS 설정 추가 (필요시 Phase 추가)
