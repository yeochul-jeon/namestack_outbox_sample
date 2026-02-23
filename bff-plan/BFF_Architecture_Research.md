# Spring Cloud Gateway 기반 BFF(Backend for Frontend) 아키텍처 및 사례 조사

본 문서는 Spring 기반의 BFF API 설계를 위한 사전 조사 자료입니다. 국내외 주요 기업의 도입 사례와 핵심 구현 패턴을 정리하여 프로젝트의 레퍼런스로 활용하고자 합니다.

## 1. 개요 및 핵심 컨셉

**BFF (Backend for Frontend)** 패턴은 특정 프론트엔드(Web, Mobile, Admin 등)의 요구사항에 맞춰 최적화된 API를 제공하는 중간 계층입니다.

Spring Cloud Gateway(이하 SCG)는 비동기/논블로킹(Netty + WebFlux) 기반으로 높은 처리량을 보장하며, 단순한 라우팅뿐만 아니라 BFF의 역할(인증, 필터링, 간단한 조합)을 수행하기에 적합합니다.

---

## 2. 국내 도입 사례 (Domestic Cases)

### 2.1 우아한형제들 (배달의민족)
*   **배경**: PHP 모놀리식 아키텍처에서 Java 마이크로서비스(MSA)로 전환하면서, 모든 클라이언트 요청의 진입점을 단일화할 필요성 대두.
*   **초기 모델**: Netflix Zuul을 도입하여 **인증(Authentication), 라우팅(Routing), 로깅(Logging)** 등의 공통 관심사(Cross-cutting concerns)를 처리.
*   **시사점**:
    *   현재의 SCG는 Zuul의 Blocking 구조 한계를 극복하기 위해 등장.
    *   **Gateway의 역할**: 개별 마이크로서비스가 인증/인가를 중복 구현하지 않도록 Gateway 앞단에서 JWT 검증 등을 일괄 처리하는 패턴이 표준화됨.

### 2.2 카카오페이 (Kakao Pay)
*   **기술 스택**: Kotlin + Spring WebFlux + Coroutines
*   **구현 방식**: 단순 라우팅을 넘어선 **Aggregator(데이터 조합)형 BFF** 구현.
    *   SCG 혹은 WebFlux 기반의 별도 서비스를 통해 여러 마이크로서비스(계좌, 사용자, 결제 등)의 API를 비동기 병렬로 호출(Call).
    *   프론트엔드가 화면을 그리기 좋은 형태(View Model)로 데이터를 가공하여 응답.
*   **시사점**:
    *   **Reactive Programming**: Gateway/BFF 계층은 I/O 대기 시간이 길기 때문에 WebFlux(Non-blocking)가 필수적임.
    *   **Coroutines**: 비동기 코드를 동기 코드처럼 작성하여 유지보수성을 높임.

### 2.3 NHN (Dooray!)
*   **사례**: 협업 도구 Dooray의 Gateway 구현.
*   **특징**: GitHub에 공개된 `mini-dooray` 아키텍처 등을 통해 SCG를 활용한 라우팅 및 필터링 구조를 엿볼 수 있음.
    *   라우트별 필터 적용 (Header 조작, 경로 재작성).

---

## 3. 해외 및 표준 아키텍처 사례 (International & Standard Patterns)

해외에서는 "Token Relay" 패턴과 "OAuth2 BFF" 패턴이 SCG 활용의 표준으로 자리 잡고 있습니다.

### 3.1 OAuth2 BFF 패턴 (The Token Relay)
프론트엔드(SPA)가 Access Token을 브라우저 저장소(LocalStorage)에 보관하는 보안 취약점(XSS 등)을 해결하기 위한 구조입니다.

*   **동작 방식**:
    1.  **Frontend**: Gateway와는 세션(Cookie) 기반으로 통신. (Token을 알지 못함)
    2.  **Gateway (BFF)**: OAuth2 Client 역할을 수행.
        *   로그인 성공 시 Access/Refresh Token을 Gateway 서버 메모리(혹은 Redis)에 저장.
        *   브라우저에는 `JSESSIONID` 쿠키만 발급 (HttpOnly, Secure).
    3.  **Token Relay**: Gateway가 요청을 받아 백엔드 마이크로서비스로 라우팅할 때, 세션에서 Token을 꺼내 HTTP Header(`Authorization: Bearer ...`)에 실어서 보냄.
*   **장점**: 최고 수준의 보안성 확보.

### 3.2 Client-Specific Gateway
*   **Netflix 모델**: 과거 하나의 거대한 API Gateway에서 발생한 "단일 실패 지점(SPOF)" 및 "팀 간 의존성 문제"를 해결하기 위해 클라이언트별로 Gateway를 분리.
    *   `Web BFF Gateway`
    *   `Mobile BFF Gateway`
    *   `Public API Gateway`

---

## 4. 우리 프로젝트를 위한 제안 (Proposal)

위 사례들을 종합하여 본 프로젝트(Spring 기반 BFF)에 적용할 권장 아키텍처는 다음과 같습니다.

### 4.1 기술 스택
*   **Framework**: Spring Cloud Gateway (Spring Boot 3.x, Java 17/21)
*   **Engine**: Netty (기본 내장)

### 4.2 핵심 기능 정의
1.  **Router (기본)**:
    *   Path 기반 라우팅 (`/api/v1/users/**` -> User Service).
    *   Predicate를 활용한 헤더/메소드 분기.
2.  **Security (BFF 핵심)**:
    *   Global Filter에서 JWT 검증 또는 Session-Token 변환 수행.
    *   CORS 설정 중앙화.
3.  **Resilience (안정성)**:
    *   Resilience4j 연동.
    *   장애 발생 시 Fallback URI로 리다이렉트 (예: "잠시 후 다시 시도해주세요" 메시지 응답).
4.  **Observability (관측)**:
    *   요청 ID(Trace ID) 생성 및 하위 서비스 전파 (Micrometer Tracing).

이 계획을 바탕으로 실제 구현 단계로 진입하는 것을 추천합니다.
