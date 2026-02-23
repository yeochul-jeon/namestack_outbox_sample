# RestApiClient vs HTTP Service Interface 분석

## 1. 개요

이 문서는 CJ O Shopping BFF 라이브러리의 `RestApiClient`와 Spring Boot 4.x의 `HTTP Service Interface`를 비교 분석하고, 두 시스템의 통합 방안을 제시합니다.

### RestApiClient

`RestApiClient`는 CJ O Shopping에서 개발한 REST API 클라이언트 라이브러리로, YAML 설정을 통해 여러 외부 API 클라이언트를 관리합니다.

**핵심 특징:**
- 싱글톤 Spring Component
- YAML 기반 설정 (SSL, 프록시, 커넥션 풀, 타임아웃)
- Apache HttpClient 5 기반
- `RestApiOperation` 인터페이스를 통한 일관된 API

### HTTP Service Interface

Spring Boot 4.x에서 도입된 선언적 HTTP 클라이언트로, 인터페이스와 어노테이션만으로 HTTP 클라이언트를 정의합니다.

**핵심 특징:**
- 선언적 인터페이스 정의
- `@ImportHttpServices`로 자동 Bean 등록 (Spring Boot 4.x)
- 컴파일 타임 타입 안전성
- Feign/Retrofit 스타일의 간결한 코드

---

## 2. 비교 분석표

### 설정 방식

| 항목 | RestApiClient | HTTP Service Interface |
|------|---------------|------------------------|
| 설정 위치 | `rest.api-clients.*` | `spring.http.serviceclient.*` |
| 기본 URL | YAML `base-url` | YAML `base-url` |
| 타임아웃 | `connect-timeout`, `response-timeout`, `socket-timeout` | `connect-timeout`, `read-timeout` |
| 설정 복잡도 | 높음 (많은 옵션) | 낮음 (간단한 옵션) |

### 기능 비교

| 기능 | RestApiClient | HTTP Service Interface |
|------|---------------|------------------------|
| SSL/TLS 설정 | 완전 지원 (insecure trust manager 포함) | 제한적 |
| 프록시 설정 | 완전 지원 | 미지원 |
| 커넥션 풀 | 상세 설정 가능 | 기본 설정만 |
| 압축 | 지원 | 지원 |
| 요청/응답 로깅 | 지원 | 설정 필요 |
| 멀티파트 업로드 | 지원 | 지원 |
| 에러 핸들링 | `RestApiClientException` 래핑 | 표준 Spring 예외 |

### 사용 방식

| 항목 | RestApiClient | HTTP Service Interface |
|------|---------------|------------------------|
| API 정의 | 메서드 호출 (런타임) | 인터페이스 선언 (컴파일 타임) |
| 타입 안전성 | `ParameterizedTypeReference` 사용 | 메서드 시그니처로 보장 |
| 코드량 | 많음 | 적음 |
| 유연성 | 높음 (동적 URL, 헤더) | 제한적 |
| 학습 곡선 | 낮음 | 낮음 |

---

## 3. 아키텍처 다이어그램

### RestApiClient 아키텍처

```
┌─────────────────────────────────────────────────────────────┐
│                        Application                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│    ┌─────────────┐        ┌─────────────────────────────┐   │
│    │   Service   │───────▶│      RestApiClient          │   │
│    └─────────────┘        │  (RestApiOperation 구현)    │   │
│                           └──────────────┬──────────────┘   │
│                                          │                  │
│                                          ▼                  │
│                           ┌─────────────────────────────┐   │
│                           │    Map<String, RestClient>  │   │
│                           │  ┌────────┐  ┌────────┐     │   │
│                           │  │client-a│  │client-b│ ... │   │
│                           │  └───┬────┘  └───┬────┘     │   │
│                           └──────┼───────────┼──────────┘   │
│                                  │           │              │
│                                  ▼           ▼              │
│                           ┌─────────────────────────────┐   │
│                           │     RestClientFactory       │   │
│                           │  (Apache HttpClient 5 기반)  │   │
│                           │  - SSL/TLS 설정             │   │
│                           │  - 프록시 설정              │   │
│                           │  - 커넥션 풀 관리           │   │
│                           └─────────────────────────────┘   │
│                                                             │
└─────────────────────────────────────────────────────────────┘

YAML 설정:
rest:
  api-clients:
    client-a:
      base-url: https://api-a.example.com
      ssl: ...
      proxy: ...
      pool: ...
```

### HTTP Service Interface 아키텍처

```
┌─────────────────────────────────────────────────────────────┐
│                        Application                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│    ┌─────────────┐        ┌─────────────────────────────┐   │
│    │   Service   │───────▶│     TodoClient (Interface)  │   │
│    └─────────────┘        │     @HttpExchange("/todos") │   │
│                           └──────────────┬──────────────┘   │
│                                          │                  │
│                                          ▼                  │
│                           ┌─────────────────────────────┐   │
│                           │     JDK Dynamic Proxy       │   │
│                           │  (HttpServiceProxyFactory)  │   │
│                           └──────────────┬──────────────┘   │
│                                          │                  │
│                                          ▼                  │
│                           ┌─────────────────────────────┐   │
│                           │   RestClientAdapter         │   │
│                           │      ↓                      │   │
│                           │   RestClient (Spring)       │   │
│                           └─────────────────────────────┘   │
│                                                             │
│    @ImportHttpServices(group = "todos", types = TodoClient.class)
│                                                             │
└─────────────────────────────────────────────────────────────┘

YAML 설정:
spring:
  http:
    serviceclient:
      todos:
        base-url: https://api.example.com
```

---

## 4. 통합 가능성 분석

### @ImportHttpServices 제약사항

`@ImportHttpServices`는 Spring Boot 4.x의 자체 설정 체계(`spring.http.serviceclient.*`)를 사용하며, RestApiClient의 설정(`rest.api-clients.*`)과 호환되지 않습니다.

**제약 사항:**
- SSL 인증서 상세 설정 불가
- 프록시 설정 불가
- 커넥션 풀 상세 설정 불가
- RestApiClient와 설정 공유 불가

### 통합이 필요한 시나리오

1. **기존 RestApiClient 프로젝트에 HTTP Service Interface 도입**
   - 이미 복잡한 SSL/프록시 설정이 완료된 프로젝트
   - 점진적으로 선언적 클라이언트로 마이그레이션 희망

2. **동일한 API에 두 가지 방식 모두 사용**
   - 일부 기능은 RestApiClient의 유연성 필요
   - 일부 기능은 HTTP Service Interface의 타입 안전성 필요

3. **리소스 효율화**
   - 동일한 RestClient 인스턴스 공유
   - 커넥션 풀 중복 방지

---

## 5. 권장 사용 패턴

### 언제 RestApiClient를 사용할 것인가?

| 시나리오 | 권장 |
|----------|------|
| 복잡한 SSL/TLS 설정이 필요 | RestApiClient |
| 프록시 서버 경유 필요 | RestApiClient |
| 커넥션 풀 세밀한 제어 필요 | RestApiClient |
| 동적 URL/헤더 구성 필요 | RestApiClient |
| 런타임에 클라이언트 선택 필요 | RestApiClient |

### 언제 HTTP Service Interface를 사용할 것인가?

| 시나리오 | 권장 |
|----------|------|
| 단순한 REST API 호출 | HTTP Service Interface |
| 타입 안전성이 중요 | HTTP Service Interface |
| 코드 간결성 중시 | HTTP Service Interface |
| API 계약 명확화 필요 | HTTP Service Interface |
| 새 프로젝트 시작 | HTTP Service Interface |

### 언제 브릿지 방식을 사용할 것인가?

| 시나리오 | 권장 |
|----------|------|
| 기존 RestApiClient 설정 재사용 | 브릿지 |
| SSL/프록시 + 타입 안전성 모두 필요 | 브릿지 |
| 점진적 마이그레이션 | 브릿지 |
| 두 방식 혼용 | 브릿지 |

---

## 6. 브릿지 솔루션

### HttpServiceClientFactory

`HttpServiceClientFactory`는 RestApiClient의 RestClient를 사용하여 HTTP Service Interface 프록시를 생성하는 브릿지 클래스입니다.

```java
@Component
public class HttpServiceClientFactory {
    private final RestApiClient restApiClient;

    public <T> T createClient(String clientName, Class<T> serviceType) {
        RestClient restClient = restApiClient.getClient(clientName);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(restClient))
            .build();
        return factory.createClient(serviceType);
    }
}
```

### 브릿지 아키텍처

```
┌─────────────────────────────────────────────────────────────┐
│                        Application                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│    ┌─────────────┐                                          │
│    │   Service   │                                          │
│    └──────┬──────┘                                          │
│           │                                                 │
│           ├────────────┬────────────────────────┐           │
│           │            │                        │           │
│           ▼            ▼                        ▼           │
│    ┌────────────┐ ┌────────────┐ ┌─────────────────────┐    │
│    │RestApiClient│ │TodoClient │ │HttpServiceClientFactory│  │
│    │  .get()    │ │(Interface)│ │                     │    │
│    │  .post()   │ │           │ │ createClient()      │    │
│    └──────┬─────┘ └─────┬─────┘ └──────────┬──────────┘    │
│           │             │                   │               │
│           │             │                   │               │
│           │             ▼                   │               │
│           │      ┌─────────────────┐        │               │
│           │      │   JDK Proxy     │◀───────┘               │
│           │      └────────┬────────┘                        │
│           │               │                                 │
│           ▼               ▼                                 │
│    ┌─────────────────────────────────────────────────┐      │
│    │              RestClient (공유)                   │      │
│    │         (RestApiClient가 관리)                   │      │
│    └─────────────────────────────────────────────────┘      │
│                           │                                 │
│                           ▼                                 │
│    ┌─────────────────────────────────────────────────┐      │
│    │            RestClientFactory                    │      │
│    │   - SSL/TLS 설정                                │      │
│    │   - 프록시 설정                                 │      │
│    │   - 커넥션 풀 (Apache HttpClient 5)             │      │
│    └─────────────────────────────────────────────────┘      │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

### 사용 예제

```java
// Configuration
@Configuration
public class HttpServiceConfig {
    @Bean
    public TodoClient todoClient(HttpServiceClientFactory factory) {
        return factory.createClient("todo-api-rest-client", TodoClient.class);
    }
}

// YAML 설정 (RestApiClient 방식)
rest:
  api-clients:
    todo-api:
      base-url: https://api.example.com
      connect-timeout: 5s
      response-timeout: 30s
      ssl:
        enabled: true
        key-store: classpath:keystore.p12
      proxy:
        enabled: true
        host: proxy.company.com
        port: 8080
      pool:
        max-connections: 50
```

---

## 7. 결론 및 권장사항

### 권장 전략

1. **신규 프로젝트**: HTTP Service Interface + `@ImportHttpServices` 사용
   - 단순하고 타입 안전한 코드 작성 가능
   - Spring Boot 4.x 표준 방식

2. **기존 RestApiClient 프로젝트**: 브릿지 방식 도입 고려
   - 기존 설정(SSL, 프록시, 풀) 재사용
   - 점진적 마이그레이션 가능

3. **복잡한 요구사항**: RestApiClient 유지
   - 동적 URL/헤더 구성 필요
   - 런타임 클라이언트 선택 필요
   - 최대한의 유연성 필요

### 마이그레이션 로드맵

```
Phase 1: 브릿지 도입
├── HttpServiceClientFactory 추가
├── 주요 API를 HTTP Service Interface로 정의
└── 브릿지로 Bean 등록

Phase 2: 점진적 전환
├── 새 기능은 HTTP Service Interface로 개발
├── 기존 RestApiClient 호출을 점진적으로 교체
└── 테스트 커버리지 확보

Phase 3: 평가 및 결정
├── @ImportHttpServices로 완전 전환 가능성 평가
├── SSL/프록시 요구사항 재검토
└── 최종 아키텍처 결정
```

### 파일 구조

```
src/main/java/cj/oshopping/common/autoconfigure/restclient/
├── RestApiClient.java              # 기존 클라이언트
├── RestClientFactory.java          # RestClient 팩토리
└── HttpServiceClientFactory.java   # 브릿지 팩토리 (신규)

src/test/java/cj/oshopping/common/autoconfigure/httpservice/
├── Todo.java                       # DTO
├── TodoClient.java                 # HTTP Service Interface
├── HttpServiceBridgeConfig.java    # 브릿지 설정 예제
├── HttpServiceBridgeTest.java      # 브릿지 테스트
├── HTTP_SERVICE_INTERFACE_GUIDE.md # 사용 가이드
└── RESTAPICLIENT_VS_HTTPSERVICE_ANALYSIS.md  # 이 문서
```
