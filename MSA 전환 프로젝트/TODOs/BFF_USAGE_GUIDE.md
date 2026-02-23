# BFF Framework 사용 가이드

## 1. 빠른 시작 (Quick Start)

### 1.1 기본 라우트 정의

```java
@Configuration
public class BffRoutesConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-api", r -> r
                .path("/api/users/**")
                .clientName("user-service-rest-client")
                .restClient())
            .build();
    }
}
```

### 1.2 기본 요청 처리

```java
@Service
public class BffService {

    private final Router router;

    public BffService(Router router) {
        this.router = router;
    }

    public Object processRequest(HttpServletRequest httpRequest) {
        // 1. BffRequest 생성
        BffRequest request = DefaultBffRequest.builder()
            .method(HttpMethod.valueOf(httpRequest.getMethod()))
            .path(httpRequest.getRequestURI())
            .headers(extractHeaders(httpRequest))
            .build();

        // 2. 라우트 매칭
        Optional<Route> matchedRoute = router.route(request);
        if (matchedRoute.isEmpty()) {
            throw new NotFoundException("No route matched");
        }

        // 3. 컨텍스트 생성 및 처리
        BffContext context = new DefaultBffContext(request);
        context.setRoute(matchedRoute.get());

        // 4. Processor 실행
        matchedRoute.get().getProcessor().process(context);

        // 5. 응답 반환
        return context.getResponse().getBody();
    }
}
```

---

## 2. 라우트 정의 (RouteLocatorBuilder DSL)

### 2.1 기본 구조

```java
builder.routes()
    .route("route-id", r -> r
        // Predicate 정의
        .path("/api/**")
        .method("GET")
        // Processor 설정
        .clientName("service-rest-client")
        .restClient())
    .build();
```

### 2.2 복수 라우트 정의

```java
builder.routes()
    .route("user-api", r -> r
        .path("/api/users/**")
        .clientName("user-service-rest-client")
        .restClient())

    .route("order-api", r -> r
        .path("/api/orders/**")
        .clientName("order-service-rest-client")
        .restClient())

    .route("product-api", r -> r
        .path("/api/products/**")
        .clientName("product-service-rest-client")
        .restClient())
    .build();
```

### 2.3 우선순위 설정

```java
builder.routes()
    // 우선순위가 낮은 숫자가 먼저 매칭됨
    .route("specific-user", r -> r
        .path("/api/users/me")
        .order(0)  // 높은 우선순위
        .clientName("auth-service-rest-client")
        .restClient())

    .route("general-user", r -> r
        .path("/api/users/**")
        .order(10)  // 낮은 우선순위
        .clientName("user-service-rest-client")
        .restClient())
    .build();
```

### 2.4 메타데이터 추가

```java
builder.routes()
    .route("user-api", r -> r
        .path("/api/users/**")
        .metadata("department", "user-team")
        .metadata("version", "v2")
        .clientName("user-service-rest-client")
        .restClient())
    .build();
```

### 2.5 라우트 비활성화

```java
builder.routes()
    .route("deprecated-api", r -> r
        .path("/api/v1/**")
        .enabled(false)  // 비활성화 (매칭에서 제외)
        .clientName("legacy-service-rest-client")
        .restClient())
    .build();
```

---

## 3. Predicate 사용법

### 3.1 PathPredicate (경로 패턴 매칭)

```java
// 단일 패턴
.path("/api/users/**")

// 복수 패턴 (OR 조건)
.path("/api/users/**", "/api/members/**")

// 경로 변수 포함
.path("/api/users/{userId}")
.path("/api/users/{userId}/orders/{orderId}")
```

**지원 패턴:**

| 패턴 | 설명 | 예시 |
|------|------|------|
| `?` | 단일 문자 | `/api/user?` → `/api/user1` |
| `*` | 단일 세그먼트 내 0개 이상 문자 | `/api/*/list` → `/api/users/list` |
| `**` | 0개 이상 세그먼트 | `/api/**` → `/api/users/123/orders` |
| `{var}` | 경로 변수 | `/api/users/{id}` → `/api/users/123` |

### 3.2 MethodPredicate (HTTP 메서드)

```java
// 단일 메서드
.method("GET")

// 복수 메서드 (OR 조건)
.method("GET", "POST")
.method("GET", "POST", "PUT", "DELETE")
```

### 3.3 HeaderPredicate (헤더 검증)

```java
// 헤더 존재 여부
.header("X-API-Key")

// 헤더 값 매칭
.header("X-API-Version", "v2")

// 정규식 패턴 매칭
.headerMatches("X-Request-Id", "[a-f0-9]{8}-.*")
```

### 3.4 CookiePredicate (쿠키 검증)

```java
// 쿠키 존재 여부
.cookie("session")

// 쿠키 값 매칭
.cookie("locale", "ko-KR")

// 정규식 패턴 매칭
.cookieMatches("token", "Bearer\\s+.*")
```

### 3.5 Predicate 조합

```java
// AND 조합 (모든 조건 만족)
builder.routes()
    .route("secured-api", r -> r
        .path("/api/admin/**")
        .and()
        .method("GET", "POST")
        .and()
        .header("Authorization")
        .clientName("admin-service-rest-client")
        .restClient())
    .build();

// 복합 조건
builder.routes()
    .route("v2-api", r -> r
        .path("/api/v2/**")
        .method("GET")
        .header("X-API-Version", "v2")
        .clientName("api-service-rest-client")
        .restClient())
    .build();
```

### 3.6 커스텀 Predicate

```java
// 커스텀 Predicate 생성
Predicate tenantPredicate = request -> {
    String tenant = request.getHeader("X-Tenant-Id");
    return tenant != null && tenant.equals("cjoshopping");
};

// 라우트에 적용
builder.routes()
    .route("tenant-api", r -> r
        .path("/api/**")
        .predicate(tenantPredicate)
        .clientName("service-rest-client")
        .restClient())
    .build();
```

### 3.7 Predicate 직접 조합

```java
// Predicate 인터페이스의 and/or/negate 사용
Predicate combined = PathPredicate.of("/api/users/**")
    .and(MethodPredicate.of("GET", "POST"))
    .and(HeaderPredicate.exists("Authorization"));

Predicate negated = MethodPredicate.of("DELETE").negate();  // DELETE가 아닌 경우

Predicate orCombined = PathPredicate.of("/api/users/**")
    .or(PathPredicate.of("/api/members/**"));
```

---

## 4. Processor 사용법

### 4.1 RestClientProcessor

RestApiClient를 사용하여 백엔드 API를 호출합니다.

```java
// 기본 사용 - 요청 경로를 그대로 전달
builder.routes()
    .route("proxy", r -> r
        .path("/api/**")
        .clientName("backend-service-rest-client")
        .restClient())
    .build();

// URI 템플릿 지정
builder.routes()
    .route("user-api", r -> r
        .path("/api/users/{userId}")
        .clientName("user-service-rest-client")
        .restClient("/users/{userId}"))  // 백엔드 경로 변환
    .build();
```

**특징:**
- 요청 HTTP 메서드를 그대로 전달
- 경로 변수 자동 치환
- 쿼리 파라미터 전달
- 헤더 복사 (hop-by-hop 헤더 제외)

### 4.2 HttpServiceProcessor

HTTP Service Interface 프록시를 통해 백엔드를 호출합니다.

```java
// HTTP Service Interface 정의
public interface UserClient {
    @GetExchange("/users/{id}")
    User getUser(@PathVariable String id);

    @GetExchange("/users")
    List<User> getUsers();

    @PostExchange("/users")
    User createUser(@RequestBody CreateUserRequest request);
}

// 메서드 이름 지정 방식
builder.routes()
    .route("get-user", r -> r
        .path("/api/users/{userId}")
        .method("GET")
        .clientName("user-service-rest-client")
        .httpService(UserClient.class, "getUser"))
    .build();

// 커스텀 호출자 방식
builder.routes()
    .route("get-user", r -> r
        .path("/api/users/{userId}")
        .method("GET")
        .clientName("user-service-rest-client")
        .httpService(UserClient.class, (client, context) -> {
            String userId = context.getRequest().getPathVariable("userId");
            return client.getUser(userId);
        }))
    .build();
```

**자동 파라미터 매핑:**
- `String` 타입: 경로 변수 순서대로 매핑
- `Long`, `Integer` 타입: 경로 변수 파싱 후 매핑
- 그 외 객체 타입: 요청 본문으로 매핑

### 4.3 커스텀 Processor

```java
// 커스텀 Processor 구현
public class LoggingProcessor implements Processor {

    private final Processor delegate;

    public LoggingProcessor(Processor delegate) {
        this.delegate = delegate;
    }

    @Override
    public void process(BffContext context) {
        log.info("Request: {} {}",
            context.getRequest().getMethod(),
            context.getRequest().getPath());

        delegate.process(context);

        log.info("Response: {}", context.getResponse().getStatusCode());
    }

    @Override
    public String getName() {
        return "Logging[" + delegate.getName() + "]";
    }
}

// 라우트에 적용
Processor restProcessor = RestClientProcessor.builder()
    .restApiClient(restApiClient)
    .clientName("service-rest-client")
    .build();

Processor loggingProcessor = new LoggingProcessor(restProcessor);

builder.routes()
    .route("logged-api", r -> r
        .path("/api/**")
        .processor(loggingProcessor))
    .build();
```

---

## 5. 전체 파이프라인 예시

### 5.1 부서별 독립 구현

```java
// === 사용자 부서 ===
@Configuration
public class UserDepartmentBffConfig {

    @Bean
    public RouteLocator userRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-list", r -> r
                .path("/api/users")
                .method("GET")
                .clientName("user-service-rest-client")
                .restClient("/users"))

            .route("user-detail", r -> r
                .path("/api/users/{userId}")
                .method("GET")
                .clientName("user-service-rest-client")
                .restClient("/users/{userId}"))

            .route("user-create", r -> r
                .path("/api/users")
                .method("POST")
                .clientName("user-service-rest-client")
                .restClient("/users"))
            .build();
    }
}

// === 주문 부서 ===
@Configuration
public class OrderDepartmentBffConfig {

    @Bean
    public RouteLocator orderRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("order-list", r -> r
                .path("/api/orders")
                .method("GET")
                .header("Authorization")  // 인증 필수
                .clientName("order-service-rest-client")
                .restClient("/orders"))

            .route("order-detail", r -> r
                .path("/api/orders/{orderId}")
                .method("GET")
                .clientName("order-service-rest-client")
                .restClient("/orders/{orderId}"))
            .build();
    }
}
```

### 5.2 Controller 통합

```java
@RestController
@RequestMapping("/bff")
public class BffController {

    private final Router router;

    public BffController(Router router) {
        this.router = router;
    }

    @RequestMapping("/**")
    public ResponseEntity<Object> proxy(HttpServletRequest httpRequest,
                                        @RequestBody(required = false) Object body) {
        // 1. BffRequest 생성
        BffRequest request = DefaultBffRequest.builder()
            .method(HttpMethod.valueOf(httpRequest.getMethod()))
            .path(httpRequest.getRequestURI().replace("/bff", ""))
            .headers(extractHeaders(httpRequest))
            .queryParams(extractQueryParams(httpRequest))
            .cookies(extractCookies(httpRequest))
            .body(body)
            .build();

        // 2. 라우트 매칭
        Optional<Route> matchedRoute = router.route(request);
        if (matchedRoute.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Route route = matchedRoute.get();

        // 3. 경로 변수 추출 및 설정
        Map<String, String> pathVars = extractPathVariables(route, request);
        BffRequest enrichedRequest = DefaultBffRequest.from(request)
            .pathVariables(pathVars)
            .build();

        // 4. 컨텍스트 생성 및 처리
        BffContext context = new DefaultBffContext(enrichedRequest);
        context.setRoute(route);
        route.getProcessor().process(context);

        // 5. 응답 생성
        BffResponse response = context.getResponse();
        return ResponseEntity
            .status(response.getStatusCode())
            .headers(response.getHeaders())
            .body(response.getBody());
    }

    private Map<String, String> extractPathVariables(Route route, BffRequest request) {
        for (Predicate predicate : route.getPredicates()) {
            Map<String, String> vars = predicate.extractPathVariables(request);
            if (!vars.isEmpty()) {
                return vars;
            }
        }
        return Map.of();
    }
}
```

---

## 6. 에러 처리

### 6.1 백엔드 에러 전파

Processor는 백엔드 에러를 자동으로 전파합니다:

```java
// Processor 내부 에러 처리 로직
try {
    // 백엔드 호출
    Object result = restApiClient.method(...);
    response.setBody(result);
    response.setStatusCode(HttpStatus.OK);

} catch (RestApiClientException e) {
    // 백엔드 에러 전파
    context.setError(e);
    response.setStatusCode(e.getStatusCode());
    response.setBody(e.getResponse());

} catch (Exception e) {
    // 내부 에러
    context.setError(e);
    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    response.setBody(Map.of("error", "Internal server error"));
}
```

### 6.2 에러 확인

```java
route.getProcessor().process(context);

if (context.hasError()) {
    Throwable error = context.getError();
    if (error instanceof RestApiClientException) {
        // 백엔드 에러 처리
        RestApiClientException apiError = (RestApiClientException) error;
        log.warn("Backend error: {} - {}", apiError.getStatusCode(), apiError.getResponse());
    }
}
```

### 6.3 라우트 미매칭 처리

```java
Optional<Route> matchedRoute = router.route(request);

if (matchedRoute.isEmpty()) {
    // 라우트를 찾지 못한 경우
    return ResponseEntity.notFound().build();
}
```

---

## 7. 확장 포인트

### 7.1 커스텀 Predicate

```java
// 역할 기반 Predicate
public class RolePredicate implements Predicate {
    private final Set<String> requiredRoles;

    public RolePredicate(String... roles) {
        this.requiredRoles = Set.of(roles);
    }

    @Override
    public boolean test(BffRequest request) {
        String rolesHeader = request.getHeader("X-User-Roles");
        if (rolesHeader == null) {
            return false;
        }
        Set<String> userRoles = Set.of(rolesHeader.split(","));
        return userRoles.stream().anyMatch(requiredRoles::contains);
    }

    public static RolePredicate of(String... roles) {
        return new RolePredicate(roles);
    }
}

// 사용
builder.routes()
    .route("admin-api", r -> r
        .path("/api/admin/**")
        .predicate(RolePredicate.of("ADMIN", "SUPER_ADMIN"))
        .clientName("admin-service-rest-client")
        .restClient())
    .build();
```

### 7.2 커스텀 Processor (캐싱)

```java
public class CachingProcessor implements Processor {
    private final Processor delegate;
    private final Cache<String, Object> cache;

    @Override
    public void process(BffContext context) {
        BffRequest request = context.getRequest();

        // GET 요청만 캐싱
        if (request.getMethod() == HttpMethod.GET) {
            String cacheKey = request.getPath() + "?" + request.getQueryParams();
            Object cached = cache.getIfPresent(cacheKey);

            if (cached != null) {
                context.getResponse().setBody(cached);
                context.getResponse().setStatusCode(HttpStatus.OK);
                return;
            }

            delegate.process(context);

            if (context.getResponse().isSuccessful()) {
                cache.put(cacheKey, context.getResponse().getBody());
            }
        } else {
            delegate.process(context);
        }
    }
}
```

### 7.3 커스텀 Router

```java
public class CanaryRouter implements Router {
    private final Router mainRouter;
    private final Router canaryRouter;
    private final double canaryPercentage;

    @Override
    public Optional<Route> route(BffRequest request) {
        // 일정 비율로 카나리 라우터 사용
        if (Math.random() < canaryPercentage) {
            Optional<Route> canaryRoute = canaryRouter.route(request);
            if (canaryRoute.isPresent()) {
                return canaryRoute;
            }
        }
        return mainRouter.route(request);
    }
}
```

---

## 8. 테스트 작성 가이드

### 8.1 Predicate 테스트

```java
@Test
void shouldMatchPath() {
    // given
    PathPredicate predicate = PathPredicate.of("/api/users/**");
    BffRequest request = DefaultBffRequest.builder()
        .method(HttpMethod.GET)
        .path("/api/users/123")
        .build();

    // when & then
    assertThat(predicate.test(request)).isTrue();
}

@Test
void shouldExtractPathVariables() {
    // given
    PathPredicate predicate = PathPredicate.of("/api/users/{userId}/orders/{orderId}");
    BffRequest request = DefaultBffRequest.builder()
        .method(HttpMethod.GET)
        .path("/api/users/123/orders/456")
        .build();

    // when
    Map<String, String> vars = predicate.extractPathVariables(request);

    // then
    assertThat(vars)
        .containsEntry("userId", "123")
        .containsEntry("orderId", "456");
}
```

### 8.2 Processor 테스트 (MockServer)

```java
@Test
void shouldProcessRequest() {
    // given - MockServer 설정
    mockServer.when(
        HttpRequest.request()
            .withMethod("GET")
            .withPath("/api/users/123")
    ).respond(
        HttpResponse.response()
            .withStatusCode(200)
            .withBody("{\"id\": 123, \"name\": \"Test\"}")
    );

    RestClientProcessor processor = RestClientProcessor.builder()
        .restApiClient(restApiClient)
        .clientName("test-service-rest-client")
        .uriTemplate("/api/users/{userId}")
        .build();

    BffRequest request = DefaultBffRequest.builder()
        .method(HttpMethod.GET)
        .path("/api/users/123")
        .pathVariable("userId", "123")
        .build();

    BffContext context = new DefaultBffContext(request);

    // when
    processor.process(context);

    // then
    assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(context.getResponse().getBody()).isNotNull();
}
```

### 8.3 Router 테스트

```java
@Test
void shouldMatchCorrectRoute() {
    // given
    RouteLocator locator = builder.routes()
        .route("user-api", r -> r
            .path("/api/users/**")
            .clientName("user-service-rest-client")
            .restClient())
        .route("order-api", r -> r
            .path("/api/orders/**")
            .clientName("order-service-rest-client")
            .restClient())
        .build();

    Router router = new DefaultRouter(List.of(locator));

    BffRequest userRequest = DefaultBffRequest.builder()
        .method(HttpMethod.GET)
        .path("/api/users/123")
        .build();

    // when
    Optional<Route> route = router.route(userRequest);

    // then
    assertThat(route).isPresent();
    assertThat(route.get().getId()).isEqualTo("user-api");
}
```

### 8.4 통합 테스트

```java
@Test
void shouldProcessFullPipeline() {
    // given - MockServer, Router 설정
    mockServer.when(
        HttpRequest.request()
            .withMethod("GET")
            .withPath("/api/users/123")
    ).respond(
        HttpResponse.response()
            .withStatusCode(200)
            .withBody("{\"id\": 123, \"name\": \"Test User\"}")
    );

    RouteLocator locator = builder.routes()
        .route("user-api", r -> r
            .path("/api/users/{userId}")
            .clientName("test-service-rest-client")
            .restClient("/api/users/{userId}"))
        .build();

    Router router = new DefaultRouter(List.of(locator));

    // 요청 생성
    BffRequest request = DefaultBffRequest.builder()
        .method(HttpMethod.GET)
        .path("/api/users/123")
        .pathVariable("userId", "123")
        .build();

    // when - 라우팅
    Optional<Route> matchedRoute = router.route(request);
    assertThat(matchedRoute).isPresent();

    // 처리
    BffContext context = new DefaultBffContext(request);
    context.setRoute(matchedRoute.get());
    matchedRoute.get().getProcessor().process(context);

    // then
    assertThat(context.getResponse().getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(context.hasError()).isFalse();
}
```

---

## 9. 설정 예시 (application.yml)

```yaml
# RestApiClient 설정 (기존 방식 그대로 사용)
rest:
  api-clients:
    user-service:
      base-url: http://user-service:8080
      connect-timeout: 5s
      response-timeout: 30s
      pool:
        max-connections: 50

    order-service:
      base-url: http://order-service:8080
      connect-timeout: 5s
      response-timeout: 30s

    product-service:
      base-url: http://product-service:8080
      connect-timeout: 5s
      response-timeout: 30s
```

**참고:** YAML에서 `user-service`로 설정하면 클라이언트 이름은 `user-service-rest-client`가 됩니다.

---

## 10. FAQ

### Q1. 왜 Spring Cloud Gateway를 사용하지 않나요?

Spring Cloud Gateway는 WebFlux 기반이며, 본 프로젝트는 Spring MVC(Servlet) 기반입니다. 또한 기존 RestApiClient 인프라를 활용하기 위해 자체 구현했습니다.

### Q2. Predicate와 Filter의 차이점은?

- **Predicate**: 요청이 라우트에 매칭되는지 검증 (boolean 반환)
- **Filter**: 요청/응답을 변환 (현재 미구현, 향후 추가 예정)

### Q3. HttpServiceProcessor vs RestClientProcessor 선택 기준은?

- **HttpServiceProcessor**: 타입 안전한 인터페이스 사용, 복잡한 파라미터 매핑
- **RestClientProcessor**: 단순 프록시, 동적 URI, 빠른 설정

### Q4. 라우트 우선순위는 어떻게 결정되나요?

1. `order` 값이 낮을수록 높은 우선순위
2. 같은 `order`면 등록 순서
3. 첫 번째로 매칭되는 라우트가 선택됨

### Q5. 동적으로 라우트를 추가/제거할 수 있나요?

네, `Router.addRoute()`, `Router.removeRoute()` 메서드를 사용하세요:

```java
router.addRoute(newRoute);
router.removeRoute("route-id");
router.refresh();  // RouteLocator에서 다시 로드
```
