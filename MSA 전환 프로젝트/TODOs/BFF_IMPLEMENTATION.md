# BFF Framework 구현 결과 문서

## 1. 프로젝트 개요

### 1.1 배경

CJ O Shopping의 BFF(Backend for Frontend) 라이브러리에 Spring Cloud Gateway 스타일의 라우팅 프레임워크를 추가하여, 부서별 독립적인 API 라우팅 및 처리 로직을 구성할 수 있도록 합니다.

### 1.2 목표

- 프로그래밍 방식(RouteLocatorBuilder DSL)의 라우트 정의 지원
- 기존 RestApiClient 및 HTTP Service Interface 인프라 활용
- Spring Cloud Gateway와 유사한 Predicate/Processor 패턴 제공
- 부서별 독립적인 RouteLocator Bean 정의 지원

---

## 2. 아키텍처

### 2.1 전체 아키텍처 다이어그램

```
┌─────────────────────────────────────────────────────────────────────┐
│                         BFF Framework                                │
├─────────────────────────────────────────────────────────────────────┤
│                                                                      │
│   Request → Router → Predicate → Processor → Response               │
│               │           │            │                             │
│               ▼           ▼            ▼                             │
│           Route       Validate     Execute                           │
│          Matching     Request      Request                           │
│                                                                      │
├─────────────────────────────────────────────────────────────────────┤
│                    Existing Infrastructure                           │
│  ┌─────────────────┐  ┌────────────────────────┐                    │
│  │  RestApiClient  │  │ HttpServiceClientFactory│                    │
│  └─────────────────┘  └────────────────────────┘                    │
└─────────────────────────────────────────────────────────────────────┘
```

### 2.2 처리 흐름

```
1. BffRequest 생성 (HTTP 요청 래핑)
          ↓
2. Router.route(request) - 매칭되는 Route 찾기
          ↓
3. Predicate.test(request) - 각 조건 검증
          ↓
4. BffContext 생성 및 Route 설정
          ↓
5. Processor.process(context) - 백엔드 API 호출
          ↓
6. BffResponse 반환
```

---

## 3. 패키지 구조

```
src/main/java/cj/oshopping/common/autoconfigure/
├── restclient/                      # 기존 (변경 없음)
│
└── bff/                             # 신규: BFF Framework
    ├── core/                        # 핵심 모델
    │   ├── BffRequest.java          # 요청 인터페이스
    │   ├── BffResponse.java         # 응답 인터페이스
    │   ├── BffContext.java          # 컨텍스트 인터페이스
    │   ├── DefaultBffRequest.java   # 기본 구현
    │   ├── DefaultBffResponse.java  # 기본 구현
    │   └── DefaultBffContext.java   # 기본 구현
    │
    ├── predicate/                   # 검증기
    │   ├── Predicate.java           # 인터페이스
    │   └── impl/                    # 내장 구현
    │       ├── PathPredicate.java
    │       ├── MethodPredicate.java
    │       ├── HeaderPredicate.java
    │       └── CookiePredicate.java
    │
    ├── processor/                   # 실행기
    │   ├── Processor.java           # 인터페이스
    │   └── impl/
    │       ├── HttpServiceProcessor.java
    │       └── RestClientProcessor.java
    │
    ├── router/                      # 라우터
    │   ├── Route.java               # 라우트 인터페이스
    │   ├── DefaultRoute.java        # 기본 구현
    │   ├── Router.java              # 라우터 인터페이스
    │   └── DefaultRouter.java       # 기본 구현
    │
    ├── config/                      # 설정
    │   ├── RouteLocator.java        # 라우트 컬렉션
    │   ├── RouteLocatorBuilder.java # 빌더 DSL
    │   └── BffAutoConfiguration.java
    │
    └── exception/
        └── BffException.java
```

---

## 4. 핵심 컴포넌트

### 4.1 Core 모델

#### BffRequest

HTTP 요청 정보를 추상화한 인터페이스입니다.

```java
public interface BffRequest {
    HttpMethod getMethod();
    String getPath();
    HttpHeaders getHeaders();
    String getHeader(String name);
    MultiValueMap<String, String> getQueryParams();
    Map<String, String> getCookies();
    Object getBody();
    Map<String, String> getPathVariables();
    Map<String, Object> getAttributes();
}
```

**주요 기능:**
- HTTP 메서드, 경로, 헤더, 쿼리 파라미터, 쿠키, 본문 접근
- 경로 변수 저장 및 조회
- 사용자 정의 속성 저장

#### BffResponse

HTTP 응답 정보를 추상화한 인터페이스입니다.

```java
public interface BffResponse {
    HttpStatusCode getStatusCode();
    void setStatusCode(HttpStatusCode statusCode);
    HttpHeaders getHeaders();
    Object getBody();
    void setBody(Object body);
    boolean isCommitted();
    void commit();
}
```

**주요 기능:**
- 상태 코드 및 헤더 설정
- 응답 본문 설정
- 응답 완료 상태 관리

#### BffContext

요청 처리 파이프라인에서 공유되는 컨텍스트입니다.

```java
public interface BffContext {
    BffRequest getRequest();
    BffResponse getResponse();
    Route getRoute();
    void setRoute(Route route);
    Map<String, Object> getAttributes();
    void setAttribute(String name, Object value);
    Instant getCreatedAt();
    void markStarted();
    void markCompleted();
    long getElapsedMillis();
    boolean hasError();
    Throwable getError();
    void setError(Throwable error);
}
```

**주요 기능:**
- 요청/응답 쌍 관리
- 매칭된 라우트 정보 저장
- 처리 시간 측정
- 에러 상태 관리

### 4.2 Predicate (검증기)

요청이 특정 조건에 맞는지 검증하는 함수형 인터페이스입니다.

```java
@FunctionalInterface
public interface Predicate {
    boolean test(BffRequest request);

    default Predicate and(Predicate other);
    default Predicate or(Predicate other);
    default Predicate negate();
    default Map<String, String> extractPathVariables(BffRequest request);
}
```

#### 내장 Predicate 구현

| Predicate | 설명 | 예시 |
|-----------|------|------|
| `PathPredicate` | Ant 스타일 경로 패턴 매칭 | `/api/users/**`, `/api/users/{userId}` |
| `MethodPredicate` | HTTP 메서드 검증 | `GET`, `POST`, `PUT` |
| `HeaderPredicate` | 헤더 존재/값/패턴 검증 | `X-API-Version: v2` |
| `CookiePredicate` | 쿠키 존재/값/패턴 검증 | `session=abc123` |

**PathPredicate 지원 패턴:**
- `?` - 단일 문자 매칭
- `*` - 단일 경로 세그먼트 내 0개 이상의 문자
- `**` - 0개 이상의 경로 세그먼트
- `{variable}` - 경로 변수 추출

### 4.3 Processor (실행기)

라우트에 매칭된 요청을 실제로 처리하여 응답을 생성합니다.

```java
public interface Processor {
    void process(BffContext context);
    String getName();
    default boolean supports(BffContext context);
    default int getOrder();
}
```

#### HttpServiceProcessor

HTTP Service Interface 프록시를 통해 백엔드를 호출합니다.

```java
HttpServiceProcessor<UserClient> processor = HttpServiceProcessor.builder()
    .factory(httpServiceClientFactory)
    .clientName("user-service-rest-client")
    .serviceType(UserClient.class)
    .methodName("getUser")
    .build();
```

**특징:**
- 프록시 인스턴스 캐싱
- 경로 변수 자동 매핑
- RestApiClientException 에러 전파

#### RestClientProcessor

RestApiClient를 직접 사용하여 백엔드를 호출합니다.

```java
RestClientProcessor processor = RestClientProcessor.builder()
    .restApiClient(restApiClient)
    .clientName("user-service-rest-client")
    .uriTemplate("/api/users/{userId}")
    .build();
```

**특징:**
- URI 템플릿 경로 변수 치환
- 쿼리 파라미터 전달
- 헤더 복사 (hop-by-hop 헤더 제외)

### 4.4 Router (라우터)

요청에 맞는 라우트를 찾고 관리합니다.

```java
public interface Router {
    Optional<Route> route(BffRequest request);
    void addRoute(Route route);
    boolean removeRoute(String routeId);
    Optional<Route> getRoute(String routeId);
    Iterable<Route> getRoutes();
    int getRouteCount();
    void clearRoutes();
    void refresh();
}
```

**DefaultRouter 특징:**
- 스레드 안전한 라우트 관리 (ConcurrentHashMap, CopyOnWriteArrayList)
- 우선순위 기반 정렬
- 동적 라우트 추가/제거
- RouteLocator에서 라우트 로드

### 4.5 Route (라우트)

요청 매칭 조건과 처리기를 정의합니다.

```java
public interface Route {
    String getId();
    int getOrder();
    List<Predicate> getPredicates();
    Processor getProcessor();
    String getClientName();
    String getUri();
    boolean isEnabled();
    Map<String, Object> getMetadata();
}
```

### 4.6 Config (설정)

#### RouteLocator

라우트 컬렉션을 정의하는 함수형 인터페이스입니다.

```java
@FunctionalInterface
public interface RouteLocator {
    Iterable<Route> getRoutes();
}
```

#### RouteLocatorBuilder

프로그래밍 방식으로 라우트를 정의하는 빌더 DSL입니다.

```java
@Bean
public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("user-api", r -> r
            .path("/api/users/**")
            .and()
            .method("GET", "POST")
            .clientName("user-service-rest-client")
            .restClient("/api/users/{userId}"))
        .build();
}
```

---

## 5. 파일 목록

### 5.1 Main Sources (22개)

| 파일 | 경로 | 설명 |
|------|------|------|
| `BffRequest.java` | `bff/core/` | 요청 래퍼 인터페이스 |
| `BffResponse.java` | `bff/core/` | 응답 래퍼 인터페이스 |
| `BffContext.java` | `bff/core/` | 실행 컨텍스트 인터페이스 |
| `DefaultBffRequest.java` | `bff/core/` | 기본 요청 구현 |
| `DefaultBffResponse.java` | `bff/core/` | 기본 응답 구현 |
| `DefaultBffContext.java` | `bff/core/` | 기본 컨텍스트 구현 |
| `Predicate.java` | `bff/predicate/` | 검증기 인터페이스 |
| `PathPredicate.java` | `bff/predicate/impl/` | 경로 패턴 매칭 |
| `MethodPredicate.java` | `bff/predicate/impl/` | HTTP 메서드 검증 |
| `HeaderPredicate.java` | `bff/predicate/impl/` | 헤더 검증 |
| `CookiePredicate.java` | `bff/predicate/impl/` | 쿠키 검증 |
| `Processor.java` | `bff/processor/` | 실행기 인터페이스 |
| `HttpServiceProcessor.java` | `bff/processor/impl/` | HTTP Service Interface 기반 |
| `RestClientProcessor.java` | `bff/processor/impl/` | RestApiClient 기반 |
| `Route.java` | `bff/router/` | 라우트 인터페이스 |
| `DefaultRoute.java` | `bff/router/` | 기본 라우트 구현 |
| `Router.java` | `bff/router/` | 라우터 인터페이스 |
| `DefaultRouter.java` | `bff/router/` | 기본 라우터 구현 |
| `RouteLocator.java` | `bff/config/` | 라우트 컬렉션 인터페이스 |
| `RouteLocatorBuilder.java` | `bff/config/` | 프로그래밍 빌더 DSL |
| `BffAutoConfiguration.java` | `bff/config/` | 자동 설정 |
| `BffException.java` | `bff/exception/` | 기본 예외 |

### 5.2 Test Sources (4개)

| 파일 | 경로 | 설명 |
|------|------|------|
| `PredicateTest.java` | `bff/predicate/` | Predicate 단위 테스트 |
| `ProcessorTest.java` | `bff/processor/` | Processor 단위 테스트 |
| `RouterTest.java` | `bff/router/` | Router 단위 테스트 |
| `BffIntegrationTest.java` | `bff/` | 통합 테스트 (MockServer) |

---

## 6. 설계 결정 사항

### 6.1 인터페이스 기반 설계

모든 핵심 컴포넌트를 인터페이스로 정의하여 확장성을 확보했습니다:
- `Predicate`, `Processor`, `Route`, `Router`, `RouteLocator`

### 6.2 빌더 패턴

복잡한 객체 생성을 위해 빌더 패턴을 적용했습니다:
- `DefaultBffRequest.Builder`
- `DefaultRoute.Builder`
- `HttpServiceProcessor.Builder`
- `RestClientProcessor.Builder`
- `RouteLocatorBuilder`

### 6.3 Spring Cloud Gateway 스타일 DSL

Spring Cloud Gateway의 `RouteLocatorBuilder` 패턴을 참조하여 직관적인 DSL을 제공합니다:

```java
builder.routes()
    .route("id", r -> r
        .path("/api/**")
        .and()
        .method("GET")
        .clientName("service-rest-client")
        .restClient())
    .build();
```

### 6.4 기존 인프라 활용

새로운 HTTP 클라이언트를 만들지 않고 기존 인프라를 활용합니다:
- `RestApiClient` - HTTP 요청 실행
- `HttpServiceClientFactory` - HTTP Service Interface 프록시 생성
- `RestClientProperties` - YAML 설정 바인딩

### 6.5 스레드 안전성

동시성을 고려한 자료구조를 사용합니다:
- `ConcurrentHashMap` - 라우트 맵
- `CopyOnWriteArrayList` - 정렬된 라우트 목록

---

## 7. 의존성

### 7.1 기존 인프라 (내부 의존성)

- `cj.oshopping.common.autoconfigure.restclient.RestApiClient`
- `cj.oshopping.common.autoconfigure.restclient.HttpServiceClientFactory`
- `cj.oshopping.common.autoconfigure.restclient.exception.RestApiClientException`

### 7.2 Spring Framework

- `org.springframework.http.HttpMethod`
- `org.springframework.http.HttpHeaders`
- `org.springframework.http.HttpStatusCode`
- `org.springframework.util.AntPathMatcher`
- `org.springframework.web.util.UriComponentsBuilder`

### 7.3 테스트

- JUnit 5
- AssertJ
- MockServer (`org.mockserver`)

---

## 8. 확장 포인트

| 확장 포인트 | 인터페이스 | 용도 | 예시 |
|-------------|-----------|------|------|
| 커스텀 Predicate | `Predicate` | 커스텀 요청 매칭 | `TenantPredicate`, `RolePredicate` |
| 커스텀 Processor | `Processor` | 커스텀 요청 처리 | `AggregationProcessor`, `CachingProcessor` |
| 커스텀 Router | `Router` | 커스텀 라우팅 로직 | `WeightedRouter`, `CanaryRouter` |
| 커스텀 Route | `Route` | 커스텀 라우트 정의 | 메타데이터 확장 |

---

## 9. 테스트 현황

### 9.1 테스트 구성

- **PredicateTest**: PathPredicate, MethodPredicate, HeaderPredicate, CookiePredicate 단위 테스트
- **ProcessorTest**: HttpServiceProcessor, RestClientProcessor 단위 테스트
- **RouterTest**: DefaultRouter 단위 테스트
- **BffIntegrationTest**: 전체 파이프라인 통합 테스트 (MockServer)

### 9.2 테스트 실행

```bash
# BFF 관련 테스트 실행
./gradlew test --tests "*bff*"

# 통합 테스트만 실행
./gradlew test --tests "BffIntegrationTest"
```

---

## 10. 버그 수정 이력

### 10.1 getResponse vs getResponseBody 문제

**문제**: `RestApiClientException.getResponse()` 메서드가 실제로는 응답 본문을 반환하는데, 메서드명이 혼동을 줄 수 있음

**해결**: 기존 API 호환성을 유지하면서 Processor에서 올바르게 사용

```java
// RestClientProcessor.java
response.setBody(e.getResponse());  // getResponse()가 응답 본문을 반환
```

### 10.2 Predicate 조합 순서

**문제**: `and()`, `or()` 메서드 체이닝 시 연산 우선순위가 명확하지 않음

**해결**: 문서화 및 괄호 사용 권장

```java
// 권장 사용법
Predicate combined = pathPredicate
    .and(methodPredicate)
    .and(headerPredicate);
```
