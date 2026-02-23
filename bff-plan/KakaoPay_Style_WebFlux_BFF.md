# 카카오페이 스타일 WebFlux BFF 아키텍처 및 구현 가이드

본 문서는 Spring Cloud Gateway(SCG)를 사용하지 않고, **Spring WebFlux**와 **Kotlin Coroutines**를 활용하여 직접 BFF(Backend for Frontend) 애플리케이션을 구축하는 방안을 다룹니다. 이는 카카오페이 등에서 복잡한 화면 데이터를 효율적으로 조합(Aggregation)하기 위해 사용하는 방식과 유사합니다.

---

## 1. 아키텍처 개요

기존의 SCG가 '라우팅'과 '필터링'에 최적화되어 있다면, **Pure WebFlux BFF**는 **'데이터 조합(Aggregation)'과 '비즈니스 로직 처리'**에 최적화된 구조입니다.

### 1.1 왜 SCG 대신 Pure WebFlux인가?
*   **복잡한 조합 로직**: 단순 프록시가 아니라, 여러 마이크로서비스(A, B, C)를 호출하여 그 결과를 하나의 JSON으로 합쳐야 할 때 SCG의 Filter/Predicate 구조는 구현 난이도가 높고 가독성이 떨어집니다.
*   **API Orchestration**: 특정 서비스 호출 결과에 따라 다른 서비스를 호출해야 하는 등 동적 흐름 제어가 필요한 경우 일반적인 애플리케이션 코드(Controller/Service)가 훨씬 유리합니다.
*   **View Model 제공**: 프론트엔드가 화면을 바로 그릴 수 있도록 데이터를 가공하는 역할은 BFF 애플리케이션 레벨에서 수행하는 것이 적합합니다.

### 1.2 핵심 기술 스택 (Kakao Pay 사례 기반)
*   **Language**: Kotlin (간결한 문법, Null Safety)
*   **Framework**: Spring Boot 3.x (WebFlux)
*   **Concurrency**: **Kotlin Coroutines** (비동기 코드를 동기 코드처럼 작성, Reactor의 복잡성 해결)
*   **Client**: `WebClient` (Non-blocking I/O)

---

## 2. 구현 패턴: Aggregator (데이터 조합)

BFF의 가장 핵심적인 역할은 **API Aggregation**입니다. 아래는 메인 홈 화면을 구성하기 위해 `User Service`, `Account Service`, `Ad Service`를 병렬로 호출하는 예제입니다.

### 2.1 Dependencies (`build.gradle.kts`)

```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}
```

### 2.2 Domain Models & DTOs

하위 서비스의 데이터 모델과 프론트엔드에 내려줄 뷰 모델을 분리합니다.

```kotlin
// 하위 서비스(Downstream) 응답 모델
data class UserInfo(val id: Long, val name: String)
data class Account(val accountNumber: String, val balance: Long)
data class Advertisement(val id: Long, val imageUrl: String)

// 프론트엔드용(Upstream) 응답 모델 (BFF Response)
data class HomeView(
    val userName: String,
    val totalBalance: Long,
    val mainBanner: String?
)
```

### 2.3 API Client (WebClient + Coroutines)

WebClient를 사용하여 비동기 호출을 수행하되, `awaitSingle()` 등을 사용하여 Coroutine 친화적으로 작성합니다.

```kotlin
@Component
class DownstreamClient(private val webClient: WebClient) {

    suspend fun getUserInfo(userId: Long): UserInfo {
        return webClient.get()
            .uri("http://user-service/users/$userId")
            .retrieve()
            .awaitBody() // Reactor Mono -> Coroutine Suspended Function 변환
    }

    suspend fun getAccounts(userId: Long): List<Account> {
        return webClient.get()
            .uri("http://account-service/accounts?userId=$userId")
            .retrieve()
            .awaitBody()
    }

    suspend fun getBannerAd(): Advertisement? {
        return try {
            webClient.get()
                .uri("http://ad-service/banner")
                .retrieve()
                .awaitBody()
        } catch (e: Exception) {
            // 광고 서버 장애가 메인 로직에 영향을 주지 않도록 null 처리 (Partial Failure)
            null
        }
    }
}
```

### 2.4 Service Layer (Parallel Execution)

`coroutineScope`와 `async`를 활용하여 여러 마이크로서비스를 **병렬(Parallel)**로 호출하고 결과를 조합합니다. 이것이 WebFlux 기반 BFF의 핵심 성능 이점입니다.

```kotlin
@Service
class HomeService(private val downstreamClient: DownstreamClient) {

    suspend fun getHomeView(userId: Long): HomeView = coroutineScope {
        // 1. 비동기 병렬 호출 시작 (Non-blocking)
        val userDeferred = async { downstreamClient.getUserInfo(userId) }
        val accountsDeferred = async { downstreamClient.getAccounts(userId) }
        val adDeferred = async { downstreamClient.getBannerAd() }

        // 2. 모든 결과가 도착할 때까지 대기 (await)
        val user = userDeferred.await()
        val accounts = accountsDeferred.await()
        val ad = adDeferred.await()

        // 3. 데이터 가공 (Aggregation & Transformation)
        HomeView(
            userName = user.name,
            totalBalance = accounts.sumOf { it.balance }, // 계좌 잔액 합산 로직
            mainBanner = ad?.imageUrl
        )
    }
}
```

### 2.5 Controller (Suspend Function)

Spring WebFlux는 Controller 메서드에 `suspend` 키워드를 직접 지원합니다.

```kotlin
@RestController
@RequestMapping("/api/v1/home")
class HomeController(private val homeService: HomeService) {

    @GetMapping("/{userId}")
    suspend fun getHome(@PathVariable userId: Long): HomeView {
        return homeService.getHomeView(userId)
    }
}
```

---

## 3. Resilience (회복 탄력성) 전략

SCG를 사용하지 않으므로, Circuit Breaker나 Timeout 처리를 직접 구현하거나 라이브러리를 활용해야 합니다.

### 3.1 Partial Failure (부분 실패) 처리
BFF는 여러 서비스 중 하나가 실패하더라도, 가능한 부분은 응답해주는 것이 UX에 좋습니다. 위 `DownstreamClient` 예제의 광고(Ad) 호출 부분처럼 `try-catch`로 감싸거나, `supervisorScope`를 사용하여 개별 코루틴의 실패가 전체로 전파되지 않도록 해야 합니다.

```kotlin
// 예시: supervisorScope를 사용한 안전한 병렬 처리
suspend fun getSafeHomeView(userId: Long): HomeView = supervisorScope {
    val userDeferred = async { downstreamClient.getUserInfo(userId) }
    // ...
}
```

### 3.2 Timeout 설정
WebClient 설정 시 타임아웃을 명시해야, 하위 서비스 장애 시 BFF의 스레드 풀(Thread Pool)이 고갈되는 것을 방지할 수 있습니다.

```kotlin
@Bean
fun webClient(): WebClient {
    val httpClient = HttpClient.create()
        .responseTimeout(Duration.ofMillis(3000)) // 응답 타임아웃
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000) // 연결 타임아웃

    return WebClient.builder()
        .clientConnector(ReactorClientHttpConnector(httpClient))
        .build()
}
```

---

## 4. 요약 및 제언

| 특징 | Spring Cloud Gateway (SCG) | Pure WebFlux BFF (Controller) |
| :--- | :--- | :--- |
| **주 목적** | 라우팅, 공통 필터(인증/인가) | **데이터 조합**, 복잡한 비즈니스 로직, 뷰 모델링 |
| **개발 방식** | 설정(YAML) 위주 + Custom Filter | **코드(Kotlin/Java) 위주** |
| **데이터 가공** | 가능하지만 복잡함 (BodyRewrite) | **매우 용이함 (DTO Mapping)** |
| **카카오페이 방식** | - | **선호됨 (WebFlux + Coroutines)** |

**결론적으로**, 단순 API 패스스루가 아니라 **"화면에 특화된 데이터 조합"**이 주 목적이라면, 제안하신 대로 **SCG를 제거하고 WebFlux 기반의 애플리케이션으로 구성하는 것**이 개발 생산성과 유지보수 측면에서 카카오페이 사례와 같이 훨씬 효율적인 선택이 될 것입니다.
