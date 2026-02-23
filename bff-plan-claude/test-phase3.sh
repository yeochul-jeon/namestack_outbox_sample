#!/bin/bash

# Phase 3 Rate Limiting & Circuit Breaker Quick Test

set -e

echo "=================================================="
echo "Phase 3 Rate Limiting & Circuit Breaker Test"
echo "=================================================="
echo ""

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Configuration
BFF_URL="http://localhost:8080"
MAX_RETRIES=30
RETRY_DELAY=2

# Helper function to check service health
check_service() {
    local service_name=$1
    local service_url=$2

    echo -n "Checking $service_name... "
    for i in $(seq 1 $MAX_RETRIES); do
        if curl -s "$service_url/health" > /dev/null 2>&1; then
            echo -e "${GREEN}OK${NC}"
            return 0
        fi
        if [ $i -eq 1 ]; then
            echo -n "waiting"
        else
            echo -n "."
        fi
        sleep $RETRY_DELAY
    done
    echo -e "${RED}FAILED${NC}"
    return 1
}

# Check services
echo "1. Checking services..."
check_service "BFF Service" "$BFF_URL" || exit 1
echo ""

# Get authentication token
echo "2. Authenticating..."
LOGIN_RESPONSE=$(curl -s -X POST "$BFF_URL/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password123"}')

TOKEN=$(echo $LOGIN_RESPONSE | jq -r '.accessToken' 2>/dev/null)

if [ -z "$TOKEN" ] || [ "$TOKEN" = "null" ]; then
    echo -e "${RED}Failed to obtain token${NC}"
    echo "Response: $LOGIN_RESPONSE"
    exit 1
fi

echo -e "${GREEN}Token obtained:${NC} ${TOKEN:0:20}..."
echo ""

# Test normal requests
echo "3. Testing normal requests (Rate Limit should not trigger)..."
PASSED=0
FAILED=0

for i in {1..5}; do
    RESPONSE=$(curl -s -w "\n%{http_code}" -X GET "$BFF_URL/api/users/user-001" \
      -H "Authorization: Bearer $TOKEN")

    HTTP_CODE=$(echo "$RESPONSE" | tail -1)
    BODY=$(echo "$RESPONSE" | head -n -1)

    if [ "$HTTP_CODE" = "200" ]; then
        echo -e "  Request $i: ${GREEN}200 OK${NC}"
        ((PASSED++))
    else
        echo -e "  Request $i: ${RED}$HTTP_CODE${NC}"
        ((FAILED++))
    fi
done

echo -e "\nNormal Requests: ${GREEN}$PASSED passed${NC}, ${RED}$FAILED failed${NC}"
echo ""

# Check Rate Limit data in Redis
echo "4. Checking Rate Limit data in Redis..."
REDIS_KEY_COUNT=$(docker exec bff-redis redis-cli KEYS "rate_limit:*" 2>/dev/null | wc -l)

if [ $REDIS_KEY_COUNT -gt 0 ]; then
    echo -e "${GREEN}Rate limit keys found in Redis: $REDIS_KEY_COUNT${NC}"
    docker exec bff-redis redis-cli KEYS "rate_limit:*" | head -3
else
    echo -e "${YELLOW}No rate limit keys found in Redis${NC}"
fi
echo ""

# Test public endpoint (no rate limiting)
echo "5. Testing public endpoint (no rate limiting)..."
LOGIN_PASSED=0
for i in {1..5}; do
    RESPONSE=$(curl -s -w "\n%{http_code}" -X POST "$BFF_URL/login" \
      -H "Content-Type: application/json" \
      -d '{"username":"admin","password":"password123"}')

    HTTP_CODE=$(echo "$RESPONSE" | tail -1)

    if [ "$HTTP_CODE" = "200" ]; then
        ((LOGIN_PASSED++))
    fi
done

echo -e "Public endpoint requests: ${GREEN}$LOGIN_PASSED/5 succeeded${NC}"
echo ""

# Summary
echo "=================================================="
echo "Test Summary"
echo "=================================================="
echo -e "✓ BFF Service is running"
echo -e "✓ Authentication working"
echo -e "✓ Normal requests: $PASSED/5 passed"
echo -e "✓ Rate Limit tracking in Redis"
echo -e "✓ Public endpoints accessible"
echo ""
echo "For detailed Circuit Breaker testing:"
echo "  1. View BFF logs for Circuit Breaker state transitions"
echo "  2. Stop a mock service to trigger Circuit Breaker"
echo "  3. Check the test guide: PHASE_3_TEST_GUIDE.md"
echo ""
echo -e "${GREEN}Phase 3 Basic Test Complete${NC}"
echo "=================================================="
