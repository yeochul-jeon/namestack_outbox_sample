#!/bin/bash

# 로컬 테스트 스크립트
# BFF와 Mock 마이크로서비스들을 실행하고 API 테스트 수행

set -e

echo "=================================================="
echo "BFF Service - 로컬 테스트 시작"
echo "=================================================="

# 1. Mock 마이크로서비스 시작
echo ""
echo "[1] Mock 마이크로서비스 시작..."
echo "- User Service (port 8081)"
echo "- Order Service (port 8082)"
echo "- Payment Service (port 8083)"
echo ""

# Kill any existing services
pkill -f "user-service.py" || true
pkill -f "order-service.py" || true
pkill -f "payment-service.py" || true
sleep 1

# Start mock services in background
cd mock-services

python3 user-service.py > /tmp/user-service.log 2>&1 &
USER_PID=$!
echo "User Service PID: $USER_PID"

python3 order-service.py > /tmp/order-service.log 2>&1 &
ORDER_PID=$!
echo "Order Service PID: $ORDER_PID"

python3 payment-service.py > /tmp/payment-service.log 2>&1 &
PAYMENT_PID=$!
echo "Payment Service PID: $PAYMENT_PID"

cd ..

# Wait for services to start
echo ""
echo "서비스 시작 대기 중... (2초)"
sleep 2

# 2. BFF Service 시작
echo ""
echo "[2] BFF Service 시작..."
pkill -f "bff-service" || true
sleep 1

java -jar bff-service/target/bff-service-1.0.0.jar --spring.profiles.active=local > /tmp/bff-service.log 2>&1 &
BFF_PID=$!
echo "BFF Service PID: $BFF_PID"

# Wait for BFF to start
echo "BFF 시작 대기 중... (3초)"
sleep 3

# 3. 헬스 체크
echo ""
echo "[3] 헬스 체크..."
echo ""

echo "User Service 헬스 체크:"
curl -s http://localhost:8081/health | python3 -m json.tool || echo "❌ User Service 미응답"

echo ""
echo "Order Service 헬스 체크:"
curl -s http://localhost:8082/health | python3 -m json.tool || echo "❌ Order Service 미응답"

echo ""
echo "Payment Service 헬스 체크:"
curl -s http://localhost:8083/health | python3 -m json.tool || echo "❌ Payment Service 미응답"

echo ""
echo "BFF Service 헬스 체크:"
curl -s http://localhost:8080/health | python3 -m json.tool || echo "❌ BFF Service 미응답"

# 4. API 테스트
echo ""
echo "[4] API 테스트 시작..."
echo ""

# User API
echo "📋 User API 테스트:"
echo "  GET /api/users (모든 사용자 조회)"
curl -s http://localhost:8080/api/users | python3 -m json.tool

echo ""
echo "  GET /api/users/user-001 (특정 사용자 조회)"
curl -s http://localhost:8080/api/users/user-001 | python3 -m json.tool

# Order API
echo ""
echo "📋 Order API 테스트:"
echo "  GET /api/orders (모든 주문 조회)"
curl -s http://localhost:8080/api/orders | python3 -m json.tool

echo ""
echo "  GET /api/orders?userId=user-001 (특정 사용자의 주문 조회)"
curl -s http://localhost:8080/api/orders?userId=user-001 | python3 -m json.tool

# Payment API
echo ""
echo "📋 Payment API 테스트:"
echo "  GET /api/payments (모든 결제 조회)"
curl -s http://localhost:8080/api/payments | python3 -m json.tool

echo ""
echo "  GET /api/payments?userId=user-001 (특정 사용자의 결제 조회)"
curl -s http://localhost:8080/api/payments?userId=user-001 | python3 -m json.tool

# 5. 정리
echo ""
echo "[5] 테스트 완료"
echo ""
echo "실행 중인 프로세스:"
echo "- BFF Service (PID: $BFF_PID, port 8080)"
echo "- User Service (PID: $USER_PID, port 8081)"
echo "- Order Service (PID: $ORDER_PID, port 8082)"
echo "- Payment Service (PID: $PAYMENT_PID, port 8083)"
echo ""
echo "로그 파일:"
echo "- BFF: /tmp/bff-service.log"
echo "- User: /tmp/user-service.log"
echo "- Order: /tmp/order-service.log"
echo "- Payment: /tmp/payment-service.log"
echo ""
echo "프로세스 종료하려면 다음 명령어 실행:"
echo "  pkill -f 'bff-service.jar' || true"
echo "  pkill -f 'user-service.py' || true"
echo "  pkill -f 'order-service.py' || true"
echo "  pkill -f 'payment-service.py' || true"
echo ""
echo "=================================================="
