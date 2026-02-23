#  RestApiClient
## RestApiClient 란?

Spring Boot의 `RestClient`를 기반으로 한 동기 HTTP 클라이언트입니다.
YAML 설정을 통해 여러 API 서버에 대한 클라이언트를 손쉽게 관리할 수 있습니다.

### 패키지 구조

```
client/restclient/
├── RestApiClient.java          # 메인 클라이언트 컴포넌트
├── RestApiOperation.java       # REST 작업 인터페이스
├── config/
│   ├── RestApiClientConfig.java        # Spring 자동 설정
│   ├── RestApiClientMapProperties.java # YAML 바인딩
│   ├── RestClientProperties.java       # 개별 클라이언트 설정
│   ├── RestClientDefaults.java         # 기본값 상수
│   └── RestClientFactory.java          # RestClient 팩토리
└── exception/
    └── RestApiClientException.java     # HTTP 에러 예외
```

### YAML 설정

`application.yml`에서 여러 API 클라이언트를 설정할 수 있습니다:

```yaml
rest:
  api-clients:
    # 상품 서비스 클라이언트
    item-service:
      base-url: http://item-api.example.com
      connect-timeout: 5s
      response-timeout: 30s
      socket-timeout: 30s
      connection-request-timeout: 5s
      compression: true
      pool:
        max-connections: 200
        max-connections-per-route: 20
        max-idle-time: 60s
        max-life-time: 5m
        eviction-interval: 30s
        validate-after-inactivity: 2s

    # 주문 서비스 클라이언트
    order-service:
      base-url: http://order-api.example.com
      connect-timeout: 3s
      response-timeout: 10s
      pool:
        max-connections: 100
        max-connections-per-route: 10

    # 프록시 사용 클라이언트
    external-api:
      base-url: https://external-api.example.com
      proxy:
        type: HTTP
        host: proxy.example.com
        port: 8080
        username: user
        password: pass
        non-proxy-hosts: localhost|127.0.0.1

    # SSL 인증서 사용 클라이언트
    secure-api:
      base-url: https://secure-api.example.com
      ssl:
        use-insecure-trust-manager: false  # 개발용만 true
        key-store: classpath:keystore.jks
        key-store-type: JKS
        key-store-password: changeit
        trust-store: classpath:truststore.jks
        trust-store-type: JKS
        trust-store-password: changeit
```

### 설정 옵션 상세

#### 기본 설정

| 속성 | 설명 | 기본값 |
|-----|------|--------|
| `base-url` | API 서버 기본 URL | (필수) |
| `connect-timeout` | 연결 타임아웃 | 5s |
| `response-timeout` | 응답 타임아웃 | 30s |
| `socket-timeout` | 소켓 읽기 타임아웃 | 30s |
| `connection-request-timeout` | 커넥션 풀 대기 타임아웃 | 5s |
| `compression` | 응답 압축 사용 | true |

#### Connection Pool 설정 (`pool`)

| 속성 | 설명 | 기본값 |
|-----|------|--------|
| `max-connections` | 최대 커넥션 수 | 200 |
| `max-connections-per-route` | 라우트당 최대 커넥션 | 20 |
| `max-idle-time` | 유휴 커넥션 최대 유지 시간 | 60s |
| `max-life-time` | 커넥션 최대 수명 | 5m |
| `eviction-interval` | 유휴 커넥션 제거 주기 | 30s |
| `validate-after-inactivity` | 비활성 후 유효성 검사 간격 | 2s |

#### Proxy 설정 (`proxy`)

| 속성 | 설명 | 기본값 |
|-----|------|--------|
| `type` | 프록시 타입 (HTTP, HTTPS, SOCKS4, SOCKS5) | HTTP |
| `host` | 프록시 호스트 | - |
| `port` | 프록시 포트 | - |
| `username` | 프록시 인증 사용자명 | - |
| `password` | 프록시 인증 비밀번호 | - |
| `non-proxy-hosts` | 프록시 우회 호스트 패턴 | - |

#### SSL 설정 (`ssl`)

| 속성 | 설명 | 기본값 |
|-----|------|--------|
| `use-insecure-trust-manager` | 모든 인증서 신뢰 (개발용) | false |
| `key-store` | 키 저장소 경로 | - |
| `key-store-type` | 키 저장소 유형 | JKS |
| `key-store-password` | 키 저장소 비밀번호 | - |
| `key-password` | 키 비밀번호 | - |
| `trust-store` | 신뢰 저장소 경로 | - |
| `trust-store-type` | 신뢰 저장소 유형 | JKS |
| `trust-store-password` | 신뢰 저장소 비밀번호 | - |

### 사용법

#### 1. Bean 주입

```java
@Service
public class ItemService {

    private final RestApiClient restApiClient;

    public ItemService(RestApiClient restApiClient) {
        this.restApiClient = restApiClient;
    }
}
```

#### 2. GET 요청

```java
// 클라이언트 이름은 YAML 설정 키 + "-rest-client" suffix
String clientName = "item-service-rest-client";

// 단일 객체 조회
Item item = restApiClient.get(
    clientName,
    "/api/items/123",
    new HttpHeaders(),
    new ParameterizedTypeReference<Item>() {}
);

// 목록 조회
List<Item> items = restApiClient.get(
    clientName,
    "/api/items?category=electronics",
    new HttpHeaders(),
    new ParameterizedTypeReference<List<Item>>() {}
);

// ResponseObject 래퍼 사용
ResponseObject<Item> response = restApiClient.get(
    clientName,
    "/api/items/123",
    new HttpHeaders(),
    new ParameterizedTypeReference<ResponseObject<Item>>() {}
);
```

#### 3. POST 요청

```java
// JSON Body 전송
CreateItemRequest request = new CreateItemRequest("New Item", 10000);

Item created = restApiClient.post(
    "item-service-rest-client",
    "/api/items",
    new HttpHeaders(),
    request,
    new ParameterizedTypeReference<Item>() {}
);
```

#### 4. Multipart 파일 업로드

```java
MultiValueMap<String, Object> multipartData = new LinkedMultiValueMap<>();
multipartData.add("file", new FileSystemResource("/path/to/file.pdf"));
multipartData.add("description", "File description");

UploadResponse response = restApiClient.postMultipart(
    "item-service-rest-client",
    "/api/files/upload",
    new HttpHeaders(),
    multipartData,
    new ParameterizedTypeReference<UploadResponse>() {}
);
```

#### 5. PUT / PATCH / DELETE 요청

```java
// PUT
Item updated = restApiClient.put(
    "item-service-rest-client",
    "/api/items/123",
    new HttpHeaders(),
    updateRequest,
    new ParameterizedTypeReference<Item>() {}
);

// PATCH
Item patched = restApiClient.patch(
    "item-service-rest-client",
    "/api/items/123",
    new HttpHeaders(),
    patchRequest,
    new ParameterizedTypeReference<Item>() {}
);

// DELETE
Void result = restApiClient.delete(
    "item-service-rest-client",
    "/api/items/123",
    new HttpHeaders(),
    new ParameterizedTypeReference<Void>() {}
);
```

#### 6. 커스텀 헤더 추가

```java
HttpHeaders headers = new HttpHeaders();
headers.set("Authorization", "Bearer " + token);
headers.set("X-Request-ID", UUID.randomUUID().toString());

Item item = restApiClient.get(
    "item-service-rest-client",
    "/api/items/123",
    headers,
    new ParameterizedTypeReference<Item>() {}
);
```

### 에러 처리

HTTP 4xx/5xx 응답 시 `RestApiClientException`이 발생합니다:

```java
try {
    Item item = restApiClient.get(
        "item-service-rest-client",
        "/api/items/999",
        new HttpHeaders(),
        new ParameterizedTypeReference<Item>() {}
    );
} catch (RestApiClientException e) {
    HttpStatusCode status = e.getStatusCode();  // 404
    MediaType contentType = e.getContentType();  // application/json
    String responseBody = e.getResponse();       // {"error": "Not Found"}

    log.error("API 호출 실패: status={}, body={}", status, responseBody);
}
```

### 보안 주의사항

#### InsecureTrustManager 제한

- `use-insecure-trust-manager: true` 설정은 **개발 환경에서만** 사용
- `prod`, `production`, `real`, `live` 프로필에서는 자동으로 차단됨 (예외 발생)
- 운영 환경에서는 반드시 올바른 SSL 인증서 설정 필요

```yaml
# 잘못된 예시 (운영 환경에서 오류 발생)
ssl:
  use-insecure-trust-manager: true  # prod 프로필에서 IllegalStateException

# 올바른 예시
ssl:
  key-store: classpath:keystore.jks
  key-store-password: ${SSL_KEY_STORE_PASSWORD}
  trust-store: classpath:truststore.jks
  trust-store-password: ${SSL_TRUST_STORE_PASSWORD}
```

#### 민감 정보 보호

- API 에러 응답 본문은 DEBUG 레벨에서만 로깅됨
- 운영 환경에서는 DEBUG 로깅 비활성화 권장

```yaml
# application-prod.yml
logging:
  level:
    com.cjonstyle.purple.api.support.client.restclient: WARN
```

### 메모리 사용량 및 성능 가이드

#### 자원 관리 구조

RestApiClient는 **API별 싱글톤 패턴**을 사용하여 효율적으로 자원을 관리합니다:

```
┌─────────────────────────────────────────────────────────┐
│ RestApiClientConfig (애플리케이션 시작 시 1회 생성)        │
│   └─ Map<String, RestClient> (Bean으로 등록)              │
│        ├─ "item-service-rest-client" → RestClient A      │
│        ├─ "order-service-rest-client" → RestClient B     │
│        └─ "user-service-rest-client" → RestClient C      │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼ (요청 시 재사용)
┌─────────────────────────────────────────────────────────┐
│ RestApiClient.getClient("item-service-rest-client")     │
│   → O(1) HashMap 조회 → 기존 RestClient 반환             │
└─────────────────────────────────────────────────────────┘
```

- **요청마다 생성하지 않음** → 시작 시 1회 생성 후 재사용
- **ConcurrentHashMap** 사용 → 스레드 안전 보장
- **DisposableBean** 구현 → Spring 종료 시 자동 자원 정리

#### 클라이언트당 메모리 사용량 (기본 설정 기준)

| 구성 요소 | 계산식 | 예상 사용량 |
|----------|--------|------------|
| Connection Pool 버퍼 | ~32KB × 200 connections | ~6.4MB |
| 메타데이터 | ~8KB × 200 connections | ~1.6MB |
| 스레드 풀 | (eviction, validation 등) | ~2MB |
| RestClient 래퍼 | | ~150KB |
| **클라이언트당 합계** | | **~10MB** |

**예시:**
- 5개 API 클라이언트 = ~50MB
- 10개 API 클라이언트 = ~100MB

#### 메모리 최적화 설정

메모리 사용량을 줄이려면 Connection Pool 설정을 조정합니다:

```yaml
rest:
  api-clients:
    low-traffic-api:
      base-url: http://api.example.com
      pool:
        max-connections: 50        # 기본값 200 → 50
        max-connections-per-route: 10  # 기본값 20 → 10
        max-idle-time: 30s         # 기본값 60s → 30s
```

#### 종료 시 자원 정리

- 애플리케이션 종료 시 **5초 타임아웃**이 적용됩니다
- 각 HttpClient와 ConnectionManager가 순차적으로 정리됩니다
- 타임아웃 초과 시 강제 종료 후 다음 리소스 정리로 진행됩니다

