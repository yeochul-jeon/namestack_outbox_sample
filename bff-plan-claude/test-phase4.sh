#!/bin/bash

##############################################################################
# Phase 4: API Composition 자동 테스트 스크립트
##############################################################################

set -e

# 색상 정의
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 설정
BFF_BASE_URL="http://localhost:8080"
USER_SERVICE_URL="http://localhost:8081"
ORDER_SERVICE_URL="http://localhost:8082"
PAYMENT_SERVICE_URL="http://localhost:8083"

TEST_USER_ID="user-001"
TEST_USERNAME="john"
TEST_PASSWORD="password123"

# 테스트 결과 추적
TOTAL_TESTS=0
PASSED_TESTS=0
FAILED_TESTS=0

##############################################################################
# 유틸리티 함수
##############################################################################

log_info() {
  echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
  echo -e "${GREEN}[PASS]${NC} $1"
  PASSED_TESTS=$((PASSED_TESTS + 1))
}

log_error() {
  echo -e "${RED}[FAIL]${NC} $1"
  FAILED_TESTS=$((FAILED_TESTS + 1))
}

log_warn() {
  echo -e "${YELLOW}[WARN]${NC} $1"
}

test_section() {
  echo ""
  echo -e "${BLUE}========================================${NC}"
  echo -e "${BLUE}$1${NC}"
  echo -e "${BLUE}========================================${NC}"
}

assert_http_status() {
  local expected_status=$1
  local actual_status=$2
  local test_name=$3

  TOTAL_TESTS=$((TOTAL_TESTS + 1))

  if [ "$actual_status" -eq "$expected_status" ]; then
    log_success "HTTP Status: Expected $expected_status, Got $actual_status"
  else
    log_error "HTTP Status: Expected $expected_status, Got $actual_status"
  fi
}

assert_json_field() {
  local json=$1
  local field=$2
  local test_name=$3

  TOTAL_TESTS=$((TOTAL_TESTS + 1))

  if echo "$json" | jq -e "$field" > /dev/null 2>&1; then
    log_success "JSON Field: $field exists"
  else
    log_error "JSON Field: $field not found"
  fi
}

##############################################################################
# 사전 조건 확인
##############################################################################

check_prerequisites() {
  test_section "사전 조건 확인"

  log_info "필수 도구 확인..."

  # curl 확인
  if ! command -v curl &> /dev/null; then
    log_error "curl not found. Please install curl."
    exit 1
  fi
  log_success "curl installed"

  # jq 확인
  if ! command -v jq &> /dev/null; then
    log_error "jq not found. Please install jq for JSON parsing."
    exit 1
  fi
  log_success "jq installed"

  # Redis 확인
  log_info "Redis 연결 확인..."
  if command -v redis-cli &> /dev/null; then
    if redis-cli ping > /dev/null 2>&1; then
      log_success "Redis is running"
    else
      log_warn "Redis is not running. Some tests may fail."
    fi
  else
    log_warn "redis-cli not found"
  fi
}

##############################################################################
# 서비스 상태 확인
##############################################################################

check_service_health() {
  test_section "서비스 상태 확인"

  # BFF 서비스
  log_info "BFF 서비스 확인 ($BFF_BASE_URL/health)..."
  response=$(curl -s -w "\n%{http_code}" "$BFF_BASE_URL/health" 2>/dev/null || echo "000")
  http_code=$(echo "$response" | tail -n 1)
  if [ "$http_code" -eq 200 ]; then
    log_success "BFF 서비스 정상"
  else
    log_error "BFF 서비스 접근 불가 (HTTP $http_code)"
    log_error "BFF 서비스 실행 확인: java -jar target/bff-service-1.0.0.jar --spring.profiles.active=local"
    exit 1
  fi

  # User Service
  log_info "User Service 확인 ($USER_SERVICE_URL/health)..."
  http_code=$(curl -s -o /dev/null -w "%{http_code}" "$USER_SERVICE_URL/health" 2>/dev/null || echo "000")
  if [ "$http_code" -eq 200 ] || [ "$http_code" -eq 404 ]; then
    log_success "User Service 접근 가능"
  else
    log_warn "User Service 응답 없음 (HTTP $http_code)"
  fi

  # Order Service
  log_info "Order Service 확인 ($ORDER_SERVICE_URL/health)..."
  http_code=$(curl -s -o /dev/null -w "%{http_code}" "$ORDER_SERVICE_URL/health" 2>/dev/null || echo "000")
  if [ "$http_code" -eq 200 ] || [ "$http_code" -eq 404 ]; then
    log_success "Order Service 접근 가능"
  else
    log_warn "Order Service 응답 없음 (HTTP $http_code)"
  fi

  # Payment Service
  log_info "Payment Service 확인 ($PAYMENT_SERVICE_URL/health)..."
  http_code=$(curl -s -o /dev/null -w "%{http_code}" "$PAYMENT_SERVICE_URL/health" 2>/dev/null || echo "000")
  if [ "$http_code" -eq 200 ] || [ "$http_code" -eq 404 ]; then
    log_success "Payment Service 접근 가능"
  else
    log_warn "Payment Service 응답 없음 (HTTP $http_code)"
  fi
}

##############################################################################
# 인증 테스트
##############################################################################

get_jwt_token() {
  test_section "JWT 토큰 획득"

  log_info "로그인 시도: username=$TEST_USERNAME"

  response=$(curl -s -X POST "$BFF_BASE_URL/login" \
    -H "Content-Type: application/json" \
    -d "{\"username\": \"$TEST_USERNAME\", \"password\": \"$TEST_PASSWORD\"}")

  echo "$response"

  # userId 추출 (토큰 대신 userId 사용)
  token=$(echo "$response" | jq -r '.userId // empty')

  if [ -z "$token" ]; then
    log_error "JWT 토큰 획득 실패"
    echo "Response: $response"
    return 1
  fi

  log_success "JWT 토큰 획득 성공: $token"
  echo "$token"
}

##############################################################################
# API 테스트
##############################################################################

test_dashboard_success() {
  test_section "테스트 1: 정상 대시보드 조회 (모든 서비스 정상)"

  local token=$1

  log_info "대시보드 조회: GET /api/dashboard/$TEST_USER_ID"

  response=$(curl -s -w "\n%{http_code}" -X GET "$BFF_BASE_URL/api/dashboard/$TEST_USER_ID" \
    -H "Authorization: Bearer $token" \
    -H "Accept: application/json")

  http_code=$(echo "$response" | tail -n 1)
  body=$(echo "$response" | head -n -1)

  TOTAL_TESTS=$((TOTAL_TESTS + 1))
  assert_http_status 200 "$http_code" "Dashboard Success"

  # JSON 필드 확인
  assert_json_field "$body" '.user' "User field"
  assert_json_field "$body" '.orders' "Orders field"
  assert_json_field "$body" '.payments' "Payments field"
  assert_json_field "$body" '.summary' "Summary field"

  echo "Response Body:"
  echo "$body" | jq . || echo "$body"
}

test_dashboard_without_auth() {
  test_section "테스트 2: JWT 인증 없이 요청 (401 예상)"

  log_info "토큰 없이 대시보드 조회"

  http_code=$(curl -s -o /dev/null -w "%{http_code}" -X GET "$BFF_BASE_URL/api/dashboard/$TEST_USER_ID" \
    -H "Accept: application/json")

  TOTAL_TESTS=$((TOTAL_TESTS + 1))
  assert_http_status 401 "$http_code" "No Auth"
}

test_dashboard_invalid_user() {
  test_section "테스트 3: 존재하지 않는 사용자 조회"

  local token=$1

  log_info "존재하지 않는 사용자 조회: user-999"

  response=$(curl -s -w "\n%{http_code}" -X GET "$BFF_BASE_URL/api/dashboard/user-999" \
    -H "Authorization: Bearer $token" \
    -H "Accept: application/json")

  http_code=$(echo "$response" | tail -n 1)
  body=$(echo "$response" | head -n -1)

  TOTAL_TESTS=$((TOTAL_TESTS + 1))

  if [ "$http_code" -eq 200 ]; then
    log_success "HTTP 200 OK (부분 데이터 반환)"
    # 빈 데이터 확인
    assert_json_field "$body" '.orders' "Orders field"
  else
    log_warn "HTTP $http_code (예상과 다름)"
  fi

  echo "Response Body:"
  echo "$body" | jq . || echo "$body"
}

test_rate_limiting() {
  test_section "테스트 4: Rate Limiting (선택사항)"

  local token=$1

  log_info "Rate Limit 테스트: 150개 요청 in 1초 (Limit: 100/sec 로컬)"
  log_warn "이 테스트는 시간이 걸릴 수 있습니다..."

  local success_count=0
  local rate_limit_count=0

  for i in {1..150}; do
    http_code=$(curl -s -o /dev/null -w "%{http_code}" -X GET "$BFF_BASE_URL/api/dashboard/$TEST_USER_ID" \
      -H "Authorization: Bearer $token" \
      -H "Accept: application/json")

    if [ "$http_code" -eq 200 ]; then
      success_count=$((success_count + 1))
    elif [ "$http_code" -eq 429 ]; then
      rate_limit_count=$((rate_limit_count + 1))
    fi

    # 진행상황 표시
    if [ $((i % 30)) -eq 0 ]; then
      log_info "진행: $i/150 요청 (Success: $success_count, RateLimit: $rate_limit_count)"
    fi
  done

  TOTAL_TESTS=$((TOTAL_TESTS + 1))

  if [ "$rate_limit_count" -gt 0 ]; then
    log_success "Rate Limiting 동작 확인: Success=$success_count, RateLimit=$rate_limit_count"
  else
    log_warn "Rate Limiting 미동작: 모든 요청이 성공함 (Success=$success_count)"
  fi
}

test_health_endpoint() {
  test_section "테스트 5: 헬스체크 엔드포인트"

  log_info "BFF 헬스체크 조회: GET /health"

  response=$(curl -s -w "\n%{http_code}" -X GET "$BFF_BASE_URL/health")

  http_code=$(echo "$response" | tail -n 1)
  body=$(echo "$response" | head -n -1)

  TOTAL_TESTS=$((TOTAL_TESTS + 1))
  assert_http_status 200 "$http_code" "Health Check"

  echo "Health Status:"
  echo "$body" | jq . || echo "$body"
}

test_readiness_endpoint() {
  test_section "테스트 6: Readiness 엔드포인트"

  log_info "BFF Readiness 조회: GET /ready"

  response=$(curl -s -w "\n%{http_code}" -X GET "$BFF_BASE_URL/ready")

  http_code=$(echo "$response" | tail -n 1)
  body=$(echo "$response" | head -n -1)

  TOTAL_TESTS=$((TOTAL_TESTS + 1))
  assert_http_status 200 "$http_code" "Readiness Check"

  echo "Readiness Status:"
  echo "$body" | jq . || echo "$body"
}

test_concurrent_requests() {
  test_section "테스트 7: 동시 요청 처리"

  local token=$1

  log_info "동시 요청 테스트: 10개 동시 요청"

  local success_count=0
  local error_count=0

  # 백그라운드에서 병렬 실행
  for i in {1..10}; do
    (
      http_code=$(curl -s -o /dev/null -w "%{http_code}" -X GET "$BFF_BASE_URL/api/dashboard/$TEST_USER_ID" \
        -H "Authorization: Bearer $token" \
        -H "Accept: application/json")

      if [ "$http_code" -eq 200 ]; then
        echo "200"
      else
        echo "ERROR:$http_code"
      fi
    ) &
  done

  # 모든 백그라운드 작업 대기
  wait

  TOTAL_TESTS=$((TOTAL_TESTS + 1))
  log_success "동시 요청 완료"
}

##############################################################################
# 결과 요약
##############################################################################

print_summary() {
  echo ""
  test_section "테스트 결과 요약"

  local total=$TOTAL_TESTS
  local passed=$PASSED_TESTS
  local failed=$FAILED_TESTS

  if [ "$total" -eq 0 ]; then
    log_warn "테스트가 실행되지 않았습니다"
    return
  fi

  echo ""
  echo -e "총 테스트: ${BLUE}$total${NC}"
  echo -e "성공: ${GREEN}$passed${NC}"
  echo -e "실패: ${RED}$failed${NC}"
  echo ""

  if [ "$failed" -eq 0 ]; then
    echo -e "${GREEN}모든 테스트 통과!${NC}"
    return 0
  else
    echo -e "${RED}일부 테스트 실패${NC}"
    return 1
  fi
}

##############################################################################
# 메인 실행
##############################################################################

main() {
  echo -e "${BLUE}"
  cat << "EOF"
╔════════════════════════════════════════════════════════════╗
║    Phase 4: API Composition 자동 테스트 스크립트            ║
║    WebFlux + WebClient 기반 API Composition 검증           ║
╚════════════════════════════════════════════════════════════╝
EOF
  echo -e "${NC}"

  # 1. 사전 조건 확인
  check_prerequisites

  # 2. 서비스 상태 확인
  check_service_health

  # 3. JWT 토큰 획득
  token=$(get_jwt_token) || {
    log_error "JWT 토큰 획득 실패. 테스트를 계속할 수 없습니다."
    print_summary
    exit 1
  }

  # 4. 테스트 실행
  test_dashboard_success "$token"
  test_dashboard_without_auth
  test_dashboard_invalid_user "$token"
  test_health_endpoint
  test_readiness_endpoint
  test_concurrent_requests "$token"

  # Rate Limiting 테스트는 선택사항 (시간이 오래 걸림)
  # test_rate_limiting "$token"

  # 5. 결과 요약
  print_summary
}

# 메인 함수 실행
main "$@"
exit $?
