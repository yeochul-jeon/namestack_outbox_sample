# BFF 및 API Gateway 사용 가이드

## 목차

1. [개요](#1-개요)
2. [외부 요청 인입 및 응답 처리](#2-외부-요청-인입-및-응답-처리)
3. [API 어그리게이션](#3-api-어그리게이션)
4. [Virtual Thread + CompletableFuture 활용](#4-virtual-thread--completablefuture-활용)
5. [실전 예제](#5-실전-예제)
6. [성능 최적화 가이드](#6-성능-최적화-가이드)
7. [FAQ](#7-faq)

---

## 1. 개요

### 1.1 BFF와 API Gateway의 역할

```
┌─────────────────────────────────────────────────────────────────────────┐
│                          Frontend Layer                                  │
│                    (Web, Mobile, App, etc.)                             │
└───────────────────────────────┬─────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                     BFF / API Gateway Layer                              │
│  ┌─────────────────────────────────────────────────────────────────────┐│
│  │  Request → Router → Predicate → Processor → RestApiClient           ││
│  │     │                                             │                 ││
│  │     ▼                                             ▼                 ││
│  │  Validate                                    Backend Call           ││
│  │  Route Match                                 (+ Aggregation)        ││
│  └─────────────────────────────────────────────────────────────────────┘│
└───────────────────────────────┬─────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                        Backend Services                                  │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐    │
│  │ User Service│  │Order Service│  │Product Svc  │  │ Payment Svc │    │
│  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘    │
└─────────────────────────────────────────────────────────────────────────┘
```

### 1.2 이 프레임워크가 제공하는 기능

| 기능 | 설명 | 컴포넌트 |
|------|------|----------|
| 요청 라우팅 | URL 패턴, HTTP 메서드, 헤더 기반 라우팅 | `Router`, `Predicate` |
| 백엔드 호출 | 설정 기반 HTTP 클라이언트 | `RestApiClient`, `HttpServiceClientFactory` |
| API 어그리게이션 | 여러 백엔드 호출 결과 조합 | 커스텀 `Processor` |
| 요청/응답 변환 | 프론트 친화적 응답 포맷팅 | `Processor` |

---

## 2. 외부 요청 인입 및 응답 처리

### 2.1 기본 아키텍처

외부 요청을 처리하려면 **Spring MVC Controller** + **BFF Router**를 조합합니다.

```java
@RestController
@RequestMapping("/api")
public class BffController {

    private final Router router;

    public BffController(Router router) {
        this.router = router;
    }

    /**
     * 모든 /api/** 요청을 BFF Router로 위임
     */
    @RequestMapping("/**")
    public ResponseEntity<Object> handleRequest(HttpServletRequest request) {
        // 1. HttpServletRequest → BffRequest 변환
        BffRequest bffRequest = convertToBffRequest(request);

        // 2. 라우트 매칭
        Optional<Route> matchedRoute = router.route(bffRequest);

        if (matchedRoute.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 3. 컨텍스트 생성 및 프로세서 실행
        Route route = matchedRoute.get();
        BffContext context = DefaultBffContext.builder()
            .request(bffRequest)
            .response(new DefaultBffResponse())
            .route(route)
            .build();

        route.getProcessor().process(context);

        // 4. BffResponse → ResponseEntity 변환
        return convertToResponseEntity(context.getResponse());
    }

    private BffRequest convertToBffRequest(HttpServletRequest request) {
        return DefaultBffRequest.builder()
            .method(request.getMethod())
            .path(request.getRequestURI())
            .headers(extractHeaders(request))
            .queryParams(extractQueryParams(request))
            .body(extractBody(request))
            .build();
    }

    private ResponseEntity<Object> convertToResponseEntity(BffResponse response) {
        HttpHeaders headers = new HttpHeaders();
        response.getHeaders().forEach(headers::add);

        return ResponseEntity
            .status(response.getStatusCode())
            .headers(headers)
            .body(response.getBody());
    }
}
```

### 2.2 라우트 설정

```java
@Configuration
public class BffRoutesConfig {

    @Bean
    public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            // 사용자 API
            .route("user-api", r -> r
                .path("/api/users/**")
                .method("GET", "POST", "PUT", "DELETE")
                .clientName("user-service-rest-client")
                .restClient("/users/{userId}"))

            // 주문 API
            .route("order-api", r -> r
                .path("/api/orders/**")
                .method("GET", "POST")
                .clientName("order-service-rest-client")
                .restClient("/orders/{orderId}"))

            // 상품 API
            .route("product-api", r -> r
                .path("/api/products/**")
                .method("GET")
                .clientName("product-service-rest-client")
                .restClient("/products/{productId}"))

            .build();
    }
}
```

### 2.3 YAML 설정

```yaml
# application.yml
rest:
  api-clients:
    user-service:
      base-url: http://user-service:8080
      connect-timeout: 5s
      response-timeout: 30s
      pool:
        max-connections: 50
        max-connections-per-route: 10

    order-service:
      base-url: http://order-service:8080
      connect-timeout: 5s
      response-timeout: 30s
      pool:
        max-connections: 50

    product-service:
      base-url: http://product-service:8080
      connect-timeout: 5s
      response-timeout: 30s
      pool:
        max-connections: 100

bff:
  enabled: true
```

### 2.4 응답 처리 패턴

#### 성공 응답
```java
@Override
public void process(BffContext context) {
    try {
        Object result = restApiClient.get(
            clientName,
            uriTemplate,
            responseType,
            pathVariables
        );

        context.getResponse().setStatusCode(200);
        context.getResponse().setHeader("Content-Type", "application/json");
        context.getResponse().setBody(result);

    } catch (RestApiClientException e) {
        // 백엔드 에러 전파
        context.getResponse().setStatusCode(e.getStatusCode());
        context.getResponse().setBody(e.getResponse());
        context.setError(e);
    }
}
```

#### 응답 변환 (프론트 친화적 포맷)
```java
@Override
public void process(BffContext context) {
    // 백엔드 응답
    BackendUserDto backendUser = restApiClient.get(...);

    // 프론트 친화적 포맷으로 변환
    FrontendUserResponse response = FrontendUserResponse.builder()
        .id(backendUser.getId())
        .displayName(backendUser.getFirstName() + " " + backendUser.getLastName())
        .avatar(backendUser.getProfileImageUrl())
        .build();

    context.getResponse().setBody(response);
}
```

---

## 3. API 어그리게이션

### 3.1 어그리게이션 패턴 개요

```
┌────────────────────────────────────────────────────────────────┐
│                    Aggregation Patterns                        │
├────────────────────────────────────────────────────────────────┤
│                                                                │
│  [순차 호출]           [병렬 호출]           [의존 호출]        │
│                                                                │
│   A ──→ B ──→ C       A ────┐               A                  │
│                       B ────┼──→ Result     │                  │
│                       C ────┘               ▼                  │
│                                            B,C (동시)          │
│                                             │                  │
│                                             ▼                  │
│                                            D (B,C 결과 사용)   │
└────────────────────────────────────────────────────────────────┘
```

### 3.2 순차 어그리게이션 (기본)

```java
/**
 * 순차 API 어그리게이션 프로세서
 *
 * 단순하고 디버깅이 쉬우나, 총 응답 시간 = 각 호출 시간의 합
 */
public class SequentialAggregationProcessor implements Processor {

    private final RestApiClient restApiClient;

    @Override
    public void process(BffContext context) {
        String userId = context.getRequest().getPathVariable("userId");

        // 1. 사용자 정보 조회 (300ms)
        UserDto user = restApiClient.get(
            "user-service-rest-client",
            "/users/{userId}",
            UserDto.class,
            userId
        );

        // 2. 주문 목록 조회 (500ms)
        List<OrderDto> orders = restApiClient.get(
            "order-service-rest-client",
            "/users/{userId}/orders",
            new ParameterizedTypeReference<List<OrderDto>>() {},
            userId
        );

        // 3. 포인트 정보 조회 (200ms)
        PointDto points = restApiClient.get(
            "point-service-rest-client",
            "/users/{userId}/points",
            PointDto.class,
            userId
        );

        // 총 응답 시간: 300 + 500 + 200 = 1000ms

        // 4. 결과 조합
        UserDetailResponse response = UserDetailResponse.builder()
            .user(user)
            .orders(orders)
            .points(points)
            .build();

        context.getResponse().setBody(response);
    }

    @Override
    public String getName() {
        return "sequential-aggregation";
    }
}
```

### 3.3 병렬 어그리게이션 (CompletableFuture)

```java
/**
 * 병렬 API 어그리게이션 프로세서
 *
 * CompletableFuture를 사용하여 독립적인 API 호출을 병렬로 실행
 * 총 응답 시간 = 가장 느린 호출 시간
 */
public class ParallelAggregationProcessor implements Processor {

    private final RestApiClient restApiClient;
    private final Executor executor; // Virtual Thread Executor 주입

    public ParallelAggregationProcessor(RestApiClient restApiClient, Executor executor) {
        this.restApiClient = restApiClient;
        this.executor = executor;
    }

    @Override
    public void process(BffContext context) {
        String userId = context.getRequest().getPathVariable("userId");

        // 1. 모든 API 호출을 병렬로 시작
        CompletableFuture<UserDto> userFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "user-service-rest-client",
                "/users/{userId}",
                UserDto.class,
                userId
            ),
            executor
        );

        CompletableFuture<List<OrderDto>> ordersFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "order-service-rest-client",
                "/users/{userId}/orders",
                new ParameterizedTypeReference<List<OrderDto>>() {},
                userId
            ),
            executor
        );

        CompletableFuture<PointDto> pointsFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "point-service-rest-client",
                "/users/{userId}/points",
                PointDto.class,
                userId
            ),
            executor
        );

        // 2. 모든 결과 대기 (병렬 실행)
        // 총 응답 시간: max(300, 500, 200) = 500ms (순차 대비 50% 감소!)
        try {
            CompletableFuture.allOf(userFuture, ordersFuture, pointsFuture).join();

            UserDto user = userFuture.join();
            List<OrderDto> orders = ordersFuture.join();
            PointDto points = pointsFuture.join();

            // 3. 결과 조합
            UserDetailResponse response = UserDetailResponse.builder()
                .user(user)
                .orders(orders)
                .points(points)
                .build();

            context.getResponse().setBody(response);

        } catch (CompletionException e) {
            handleError(context, e.getCause());
        }
    }

    private void handleError(BffContext context, Throwable cause) {
        if (cause instanceof RestApiClientException ex) {
            context.getResponse().setStatusCode(ex.getStatusCode());
            context.getResponse().setBody(Map.of(
                "error", ex.getMessage(),
                "service", "aggregation"
            ));
        } else {
            context.getResponse().setStatusCode(500);
            context.getResponse().setBody(Map.of("error", "Internal server error"));
        }
        context.setError(new BffException(cause.getMessage(), cause));
    }

    @Override
    public String getName() {
        return "parallel-aggregation";
    }
}
```

### 3.4 의존적 호출 (Chained + Parallel)

```java
/**
 * 의존적 API 어그리게이션
 *
 * 일부 호출이 이전 결과에 의존하는 경우:
 * 1단계: 사용자 정보 조회 (필수)
 * 2단계: 사용자의 주문/포인트/추천상품 동시 조회 (1단계 결과 사용)
 */
public class ChainedAggregationProcessor implements Processor {

    private final RestApiClient restApiClient;
    private final Executor executor;

    @Override
    public void process(BffContext context) {
        String userId = context.getRequest().getPathVariable("userId");

        // 1단계: 사용자 정보 먼저 조회 (다른 호출에 필요한 정보 포함)
        UserDto user = restApiClient.get(
            "user-service-rest-client",
            "/users/{userId}",
            UserDto.class,
            userId
        );

        // 2단계: 사용자 등급에 따른 병렬 조회
        String membershipLevel = user.getMembershipLevel();

        CompletableFuture<List<OrderDto>> ordersFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "order-service-rest-client",
                "/users/{userId}/orders?level=" + membershipLevel,
                new ParameterizedTypeReference<List<OrderDto>>() {},
                userId
            ),
            executor
        );

        CompletableFuture<List<ProductDto>> recommendedFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "recommendation-service-rest-client",
                "/recommendations?userId={userId}&level={level}",
                new ParameterizedTypeReference<List<ProductDto>>() {},
                userId, membershipLevel
            ),
            executor
        );

        CompletableFuture<PointDto> pointsFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "point-service-rest-client",
                "/users/{userId}/points",
                PointDto.class,
                userId
            ),
            executor
        );

        // 3. 결과 대기 및 조합
        try {
            CompletableFuture.allOf(ordersFuture, recommendedFuture, pointsFuture).join();

            context.getResponse().setBody(UserDetailResponse.builder()
                .user(user)
                .orders(ordersFuture.join())
                .recommendedProducts(recommendedFuture.join())
                .points(pointsFuture.join())
                .build());

        } catch (CompletionException e) {
            handleError(context, e.getCause());
        }
    }

    @Override
    public String getName() {
        return "chained-aggregation";
    }
}
```

---

## 4. Virtual Thread + CompletableFuture 활용

### 4.1 Virtual Thread란?

Java 21+에서 도입된 경량 스레드로, 기존 Platform Thread 대비:
- **메모리 효율**: 수백 KB → 수 KB
- **생성 비용**: 저렴 (수백만 개 생성 가능)
- **블로킹 I/O**: 효율적 처리 (캐리어 스레드 자동 해제)

### 4.2 Spring Boot 4.x Virtual Thread 설정

```yaml
# application.yml
spring:
  threads:
    virtual:
      enabled: true  # 모든 요청을 Virtual Thread에서 처리
```

또는 명시적 Executor 설정:

```java
@Configuration
public class VirtualThreadConfig {

    /**
     * BFF 어그리게이션 전용 Virtual Thread Executor
     */
    @Bean("bffVirtualExecutor")
    public Executor bffVirtualExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    /**
     * 제한된 동시성이 필요한 경우 Semaphore와 조합
     */
    @Bean("boundedVirtualExecutor")
    public Executor boundedVirtualExecutor() {
        Semaphore semaphore = new Semaphore(100); // 최대 100개 동시 실행
        return task -> {
            Thread.startVirtualThread(() -> {
                try {
                    semaphore.acquire();
                    try {
                        task.run();
                    } finally {
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        };
    }
}
```

### 4.3 Virtual Thread + CompletableFuture 조합

```java
@Service
public class AggregationService {

    private final RestApiClient restApiClient;
    private final Executor virtualExecutor;

    public AggregationService(
            RestApiClient restApiClient,
            @Qualifier("bffVirtualExecutor") Executor virtualExecutor) {
        this.restApiClient = restApiClient;
        this.virtualExecutor = virtualExecutor;
    }

    /**
     * Virtual Thread를 사용한 병렬 어그리게이션
     *
     * 장점:
     * - 블로킹 I/O (RestClient)도 효율적 처리
     * - Platform Thread 고갈 걱정 없음
     * - 수천 개의 동시 요청도 처리 가능
     */
    public UserDashboard getUserDashboard(String userId) {
        // Virtual Thread에서 실행되는 CompletableFuture
        var userFuture = CompletableFuture.supplyAsync(
            () -> fetchUser(userId), virtualExecutor);

        var ordersFuture = CompletableFuture.supplyAsync(
            () -> fetchOrders(userId), virtualExecutor);

        var cartFuture = CompletableFuture.supplyAsync(
            () -> fetchCart(userId), virtualExecutor);

        var wishlistFuture = CompletableFuture.supplyAsync(
            () -> fetchWishlist(userId), virtualExecutor);

        var couponsFuture = CompletableFuture.supplyAsync(
            () -> fetchCoupons(userId), virtualExecutor);

        // 모든 Future 대기 (Virtual Thread이므로 블로킹 효율적)
        CompletableFuture.allOf(
            userFuture, ordersFuture, cartFuture, wishlistFuture, couponsFuture
        ).join();

        return UserDashboard.builder()
            .user(userFuture.join())
            .recentOrders(ordersFuture.join())
            .cart(cartFuture.join())
            .wishlist(wishlistFuture.join())
            .coupons(couponsFuture.join())
            .build();
    }

    private UserDto fetchUser(String userId) {
        return restApiClient.get("user-service-rest-client",
            "/users/{id}", UserDto.class, userId);
    }

    private List<OrderDto> fetchOrders(String userId) {
        return restApiClient.get("order-service-rest-client",
            "/users/{id}/orders?limit=5",
            new ParameterizedTypeReference<List<OrderDto>>() {}, userId);
    }

    private CartDto fetchCart(String userId) {
        return restApiClient.get("cart-service-rest-client",
            "/users/{id}/cart", CartDto.class, userId);
    }

    private List<ProductDto> fetchWishlist(String userId) {
        return restApiClient.get("wishlist-service-rest-client",
            "/users/{id}/wishlist",
            new ParameterizedTypeReference<List<ProductDto>>() {}, userId);
    }

    private List<CouponDto> fetchCoupons(String userId) {
        return restApiClient.get("coupon-service-rest-client",
            "/users/{id}/coupons",
            new ParameterizedTypeReference<List<CouponDto>>() {}, userId);
    }
}
```

### 4.4 타임아웃 처리

```java
/**
 * 개별 호출에 타임아웃 적용
 */
public UserDashboard getUserDashboardWithTimeout(String userId) {
    Duration timeout = Duration.ofSeconds(3);

    var userFuture = CompletableFuture.supplyAsync(
        () -> fetchUser(userId), virtualExecutor)
        .orTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS);

    var ordersFuture = CompletableFuture.supplyAsync(
        () -> fetchOrders(userId), virtualExecutor)
        .orTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS)
        .exceptionally(ex -> {
            // 타임아웃 시 빈 리스트 반환 (Graceful Degradation)
            log.warn("Orders fetch timeout for user: {}", userId);
            return List.of();
        });

    // ... 나머지 동일
}
```

### 4.5 부분 실패 처리 (Graceful Degradation)

```java
/**
 * 일부 서비스 실패 시에도 응답 가능하도록 처리
 */
public UserDashboard getUserDashboardGraceful(String userId) {
    var userFuture = CompletableFuture.supplyAsync(
        () -> fetchUser(userId), virtualExecutor);

    // 선택적 데이터 - 실패해도 기본값 반환
    var ordersFuture = fetchWithFallback(
        () -> fetchOrders(userId),
        List.of(),  // 실패 시 빈 리스트
        "orders"
    );

    var cartFuture = fetchWithFallback(
        () -> fetchCart(userId),
        CartDto.empty(),  // 실패 시 빈 카트
        "cart"
    );

    try {
        // 필수 데이터는 반드시 성공해야 함
        UserDto user = userFuture.get(5, TimeUnit.SECONDS);

        return UserDashboard.builder()
            .user(user)
            .recentOrders(ordersFuture.join())  // 실패해도 기본값
            .cart(cartFuture.join())            // 실패해도 기본값
            .build();

    } catch (TimeoutException | ExecutionException e) {
        throw new BffException("Failed to fetch user data", e);
    }
}

private <T> CompletableFuture<T> fetchWithFallback(
        Supplier<T> supplier, T fallback, String serviceName) {
    return CompletableFuture.supplyAsync(supplier, virtualExecutor)
        .orTimeout(3, TimeUnit.SECONDS)
        .exceptionally(ex -> {
            log.warn("Service {} failed, using fallback: {}", serviceName, ex.getMessage());
            return fallback;
        });
}
```

---

## 5. 실전 예제

### 5.1 상품 상세 페이지 (여러 서비스 조합)

```java
/**
 * 상품 상세 페이지 BFF
 *
 * 필요한 데이터:
 * - 상품 정보 (Product Service)
 * - 재고 정보 (Inventory Service)
 * - 리뷰 목록 (Review Service)
 * - 추천 상품 (Recommendation Service)
 * - 판매자 정보 (Seller Service)
 */
@Component
public class ProductDetailAggregator implements Processor {

    private final RestApiClient restApiClient;
    private final Executor virtualExecutor;

    @Override
    public void process(BffContext context) {
        String productId = context.getRequest().getPathVariable("productId");

        // 1단계: 상품 정보 (필수 - 다른 호출에 필요)
        ProductDto product = restApiClient.get(
            "product-service-rest-client",
            "/products/{productId}",
            ProductDto.class,
            productId
        );

        // 2단계: 관련 정보 병렬 조회
        var inventoryFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "inventory-service-rest-client",
                "/inventory/{productId}",
                InventoryDto.class,
                productId
            ), virtualExecutor
        ).exceptionally(ex -> InventoryDto.unknown());

        var reviewsFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "review-service-rest-client",
                "/products/{productId}/reviews?limit=10",
                new ParameterizedTypeReference<List<ReviewDto>>() {},
                productId
            ), virtualExecutor
        ).exceptionally(ex -> List.of());

        var recommendedFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "recommendation-service-rest-client",
                "/products/{productId}/related",
                new ParameterizedTypeReference<List<ProductSummaryDto>>() {},
                productId
            ), virtualExecutor
        ).exceptionally(ex -> List.of());

        var sellerFuture = CompletableFuture.supplyAsync(
            () -> restApiClient.get(
                "seller-service-rest-client",
                "/sellers/{sellerId}",
                SellerDto.class,
                product.getSellerId()
            ), virtualExecutor
        ).exceptionally(ex -> SellerDto.unknown());

        // 3. 결과 조합
        CompletableFuture.allOf(
            inventoryFuture, reviewsFuture, recommendedFuture, sellerFuture
        ).join();

        ProductDetailResponse response = ProductDetailResponse.builder()
            .product(product)
            .inventory(inventoryFuture.join())
            .reviews(reviewsFuture.join())
            .reviewCount(product.getReviewCount())
            .averageRating(product.getAverageRating())
            .relatedProducts(recommendedFuture.join())
            .seller(sellerFuture.join())
            .build();

        context.getResponse().setBody(response);
    }

    @Override
    public String getName() {
        return "product-detail-aggregator";
    }
}
```

### 5.2 라우트 등록

```java
@Configuration
public class ProductBffConfig {

    @Bean
    public RouteLocator productRoutes(
            RouteLocatorBuilder builder,
            ProductDetailAggregator productDetailAggregator) {

        return builder.routes()
            // 상품 상세 - 어그리게이션
            .route("product-detail", r -> r
                .path("/api/products/{productId}")
                .method("GET")
                .processor(productDetailAggregator))

            // 상품 목록 - 단순 프록시
            .route("product-list", r -> r
                .path("/api/products")
                .method("GET")
                .clientName("product-service-rest-client")
                .restClient("/products"))

            .build();
    }
}
```

### 5.3 주문 생성 (여러 서비스 검증 후 처리)

```java
/**
 * 주문 생성 BFF
 *
 * 흐름:
 * 1. 재고 확인 (Inventory)
 * 2. 가격 검증 (Price)
 * 3. 쿠폰 검증 (Coupon) - 병렬
 * 4. 주문 생성 (Order)
 * 5. 결제 요청 (Payment)
 */
@Component
public class OrderCreationProcessor implements Processor {

    private final RestApiClient restApiClient;
    private final Executor virtualExecutor;

    @Override
    public void process(BffContext context) {
        CreateOrderRequest request = context.getRequest().getBody(CreateOrderRequest.class);

        // 1단계: 재고 및 가격 검증 (병렬)
        var inventoryFuture = CompletableFuture.supplyAsync(
            () -> verifyInventory(request.getItems()), virtualExecutor);

        var priceFuture = CompletableFuture.supplyAsync(
            () -> verifyPrice(request.getItems()), virtualExecutor);

        var couponFuture = request.getCouponCode() != null
            ? CompletableFuture.supplyAsync(
                () -> verifyCoupon(request.getCouponCode(), request.getUserId()),
                virtualExecutor)
            : CompletableFuture.completedFuture(null);

        try {
            CompletableFuture.allOf(inventoryFuture, priceFuture, couponFuture).join();

            InventoryCheckResult inventory = inventoryFuture.join();
            PriceCheckResult price = priceFuture.join();
            CouponDto coupon = couponFuture.join();

            // 검증 실패 시 에러 응답
            if (!inventory.isAvailable()) {
                context.getResponse().setStatusCode(400);
                context.getResponse().setBody(Map.of(
                    "error", "INVENTORY_UNAVAILABLE",
                    "unavailableItems", inventory.getUnavailableItems()
                ));
                return;
            }

            // 2단계: 주문 생성
            OrderDto order = restApiClient.post(
                "order-service-rest-client",
                "/orders",
                CreateOrderCommand.builder()
                    .userId(request.getUserId())
                    .items(request.getItems())
                    .totalAmount(price.getTotalAmount())
                    .discountAmount(coupon != null ? coupon.getDiscountAmount() : 0)
                    .build(),
                OrderDto.class
            );

            // 3단계: 결제 요청
            PaymentResult payment = restApiClient.post(
                "payment-service-rest-client",
                "/payments",
                InitiatePaymentRequest.builder()
                    .orderId(order.getId())
                    .amount(order.getFinalAmount())
                    .paymentMethod(request.getPaymentMethod())
                    .build(),
                PaymentResult.class
            );

            context.getResponse().setBody(OrderCreationResponse.builder()
                .orderId(order.getId())
                .paymentUrl(payment.getRedirectUrl())
                .build());

        } catch (CompletionException e) {
            handleValidationError(context, e.getCause());
        }
    }

    @Override
    public String getName() {
        return "order-creation";
    }
}
```

---

## 6. 성능 최적화 가이드

### 6.1 병렬화 판단 기준

| 상황 | 권장 방식 | 이유 |
|------|----------|------|
| 1-2개 독립 호출 | 순차 | 병렬화 오버헤드가 더 클 수 있음 |
| 3개 이상 독립 호출 | 병렬 | 응답 시간 단축 효과 |
| 호출 간 의존성 있음 | 단계별 병렬 | 의존성 해결 후 병렬화 |
| 선택적 데이터 | 병렬 + Fallback | 실패해도 응답 가능 |

### 6.2 Virtual Thread vs Platform Thread

```java
// ❌ Platform Thread - 수천 개의 동시 요청 시 문제
ExecutorService platformExecutor = Executors.newFixedThreadPool(100);

// ✅ Virtual Thread - 수십만 개의 동시 요청도 처리 가능
Executor virtualExecutor = Executors.newVirtualThreadPerTaskExecutor();
```

### 6.3 Connection Pool 튜닝

```yaml
rest:
  api-clients:
    high-traffic-service:
      base-url: http://high-traffic:8080
      pool:
        max-connections: 200          # 높은 트래픽 서비스
        max-connections-per-route: 50
        connection-request-timeout: 1s
      connect-timeout: 3s
      response-timeout: 10s

    low-traffic-service:
      base-url: http://low-traffic:8080
      pool:
        max-connections: 20           # 낮은 트래픽 서비스
        max-connections-per-route: 10
```

### 6.4 캐싱 전략

```java
/**
 * 캐싱 래퍼 프로세서
 */
@Component
public class CachingProcessor implements Processor {

    private final Processor delegate;
    private final CacheManager cacheManager;

    @Override
    public void process(BffContext context) {
        if (!"GET".equals(context.getRequest().getMethod())) {
            delegate.process(context);
            return;
        }

        String cacheKey = generateCacheKey(context.getRequest());
        Cache cache = cacheManager.getCache("bff-cache");

        Object cached = cache.get(cacheKey, Object.class);
        if (cached != null) {
            context.getResponse().setBody(cached);
            context.getResponse().setHeader("X-Cache", "HIT");
            return;
        }

        delegate.process(context);

        if (context.getResponse().getStatusCode() == 200) {
            cache.put(cacheKey, context.getResponse().getBody());
        }
        context.getResponse().setHeader("X-Cache", "MISS");
    }
}
```

---

## 7. FAQ

### Q1: Virtual Thread를 사용하면 CompletableFuture가 필요 없나요?

**A**: 아닙니다. 둘은 보완적입니다.

- **Virtual Thread**: 블로킹 I/O를 효율적으로 처리
- **CompletableFuture**: 여러 작업의 조합, 타임아웃, 에러 처리를 선언적으로 표현

```java
// Virtual Thread + CompletableFuture 조합이 최적
CompletableFuture.supplyAsync(() -> blockingCall(), virtualExecutor)
    .thenCombine(
        CompletableFuture.supplyAsync(() -> anotherCall(), virtualExecutor),
        (a, b) -> combine(a, b)
    )
    .orTimeout(5, TimeUnit.SECONDS)
    .exceptionally(ex -> fallback());
```

### Q2: WebFlux/Reactor 대신 이 방식을 선택한 이유는?

**A**:

| 비교 항목 | Virtual Thread + CompletableFuture | WebFlux/Reactor |
|----------|-----------------------------------|-----------------|
| 학습 곡선 | 낮음 (기존 Java 지식 활용) | 높음 (반응형 패러다임) |
| 코드 가독성 | 동기 코드와 유사 | 연산자 체인 복잡 |
| 디버깅 | 쉬움 (스택 트레이스 직관적) | 어려움 |
| 성능 | 매우 좋음 (Java 21+) | 매우 좋음 |
| 기존 코드 호환 | 높음 | 낮음 (블로킹 코드 사용 불가) |

### Q3: 어떤 경우에 순차 호출을 유지해야 하나요?

**A**:

1. **호출 간 의존성이 있는 경우**: A의 결과로 B 호출
2. **단일 호출인 경우**: 병렬화 이점 없음
3. **트랜잭션이 필요한 경우**: 순서 보장 필요
4. **리소스 제약이 있는 경우**: 백엔드 서비스가 동시 호출을 감당 못함

### Q4: 에러 발생 시 전체 요청을 실패시켜야 하나요?

**A**: 데이터 중요도에 따라 결정:

```java
// 필수 데이터 - 실패 시 전체 실패
CompletableFuture<UserDto> userFuture = ...; // 예외 전파

// 선택 데이터 - 실패해도 기본값
CompletableFuture<List<RecommendationDto>> recommendationFuture = ...
    .exceptionally(ex -> List.of()); // Fallback
```

### Q5: 테스트는 어떻게 작성하나요?

**A**: MockServer를 사용한 통합 테스트:

```java
@SpringBootTest
@ActiveProfiles("test")
class ProductDetailAggregatorTest {

    private static ClientAndServer mockServer;

    @Autowired
    private ProductDetailAggregator aggregator;

    @BeforeAll
    static void startMockServer() {
        mockServer = ClientAndServer.startClientAndServer(18080);
    }

    @Test
    void shouldAggregateProductDetails() {
        // Given - 각 서비스 Mock 설정
        mockServer.when(request().withPath("/products/123"))
            .respond(response().withBody("{\"id\": 123, \"name\": \"Test\"}"));

        mockServer.when(request().withPath("/inventory/123"))
            .respond(response().withBody("{\"available\": true}"));

        // ... 추가 Mock

        // When
        BffContext context = createContext("/api/products/123");
        aggregator.process(context);

        // Then
        assertThat(context.getResponse().getStatusCode()).isEqualTo(200);
        ProductDetailResponse body = (ProductDetailResponse) context.getResponse().getBody();
        assertThat(body.getProduct().getName()).isEqualTo("Test");
    }
}
```

---

## 부록: 코드 템플릿

### A. 병렬 어그리게이션 프로세서 템플릿

```java
@Component
public class MyAggregationProcessor implements Processor {

    private final RestApiClient restApiClient;
    private final Executor virtualExecutor;

    public MyAggregationProcessor(
            RestApiClient restApiClient,
            @Qualifier("bffVirtualExecutor") Executor virtualExecutor) {
        this.restApiClient = restApiClient;
        this.virtualExecutor = virtualExecutor;
    }

    @Override
    public void process(BffContext context) {
        // 1. 요청에서 파라미터 추출
        String id = context.getRequest().getPathVariable("id");

        // 2. 병렬 호출 시작
        var future1 = async(() -> call1(id));
        var future2 = async(() -> call2(id));
        var future3 = asyncWithFallback(() -> call3(id), defaultValue());

        // 3. 결과 대기
        try {
            CompletableFuture.allOf(future1, future2, future3).join();

            // 4. 응답 조합
            context.getResponse().setBody(MyResponse.builder()
                .data1(future1.join())
                .data2(future2.join())
                .data3(future3.join())
                .build());

        } catch (CompletionException e) {
            handleError(context, e.getCause());
        }
    }

    private <T> CompletableFuture<T> async(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, virtualExecutor);
    }

    private <T> CompletableFuture<T> asyncWithFallback(Supplier<T> supplier, T fallback) {
        return CompletableFuture.supplyAsync(supplier, virtualExecutor)
            .orTimeout(3, TimeUnit.SECONDS)
            .exceptionally(ex -> fallback);
    }

    private void handleError(BffContext context, Throwable cause) {
        // 에러 처리 로직
    }

    @Override
    public String getName() {
        return "my-aggregation";
    }
}
```

### B. Virtual Thread Executor 설정 템플릿

```java
@Configuration
public class BffExecutorConfig {

    @Bean("bffVirtualExecutor")
    public Executor bffVirtualExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
```

---

*문서 작성일: 2026-01-20*
*프레임워크 버전: Spring Boot 4.0.1, Java 25*
