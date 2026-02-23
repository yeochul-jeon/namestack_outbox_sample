# Netflix BFF(Backend for Frontend) 아키텍처 사례 연구

넷플릭스는 마이크로서비스 아키텍처(MSA)의 선구자로서, 수많은 기기(TV, 모바일, 웹)에 최적화된 데이터를 제공하기 위해 BFF 패턴을 진화시켜 왔습니다. 넷플릭스의 사례는 크게 **'Reactive Aggregator'** 시대와 **'GraphQL Federation'** 시대로 나뉩니다.

---

## 1. Classic BFF: Reactive Aggregator (Zuul + RxJava)

초기 MSA 전환기에서 넷플릭스는 단일 진입점을 통해 여러 서비스를 호출하고 데이터를 조합하는 방식을 사용했습니다.

### 🛠️ 핵심 기술 스택
*   **API Gateway**: **Netflix Zuul** (초기 1.x Blocking -> 이후 2.x Non-blocking/Netty)
*   **Async/Reactive**: **RxJava** (Observable 기반의 비동기 스트림 처리)
*   **Resilience**: **Hystrix** (Circuit Breaker, Bulkhead 패턴 구현)
*   **Language**: Java, Groovy (스크립트 기반의 동적 필터링)

### 🏗️ 주요 패턴: API Aggregation
*   **Problem**: 모바일 메인 화면 하나를 그리기 위해 수십 개의 마이크로서비스를 각각 호출하면 네트워크 오버헤드가 발생함.
*   **Solution**: BFF 계층에서 RxJava를 사용하여 백엔드 서비스들을 **병렬로 호출**하고 결과를 하나로 뭉쳐서(Zip) 응답.
*   **특징**: 서버 사이드에서 클라이언트가 필요로 하는 "맞춤형 객체"를 미리 구성하여 내려줌 (Over-fetching 방지).

---

## 2. Modern BFF: GraphQL Federation (DGS Framework)

기존 REST 기반의 BFF는 새로운 화면이 생길 때마다 서버 코드를 수정해야 하는 의존성 문제가 발생했습니다. 이를 해결하기 위해 넷플릭스는 GraphQL로 전환했습니다.

### 🛠️ 핵심 기술 스택
*   **Framework**: **Netflix DGS (Domain Graph Service)**
    *   Spring Boot 기반의 GraphQL 프레임워크.
    *   Annotation 기반 프로그래밍 모델 제공.
*   **Architecture**: **Apollo Federation**
    *   여러 마이크로서비스의 GraphQL 스키마를 하나로 통합.
*   **Language**: Java / Kotlin (최신 권장)

### 🏗️ 주요 패턴: Federated Gateway
*   **작동 방식**:
    1.  각 도메인 팀은 자신의 데이터에 대한 **Subgraph**를 개발 (DGS 사용).
    2.  **Federated Gateway**는 이를 수집하여 하나의 **Supergraph**로 노출.
    3.  클라이언트는 필요한 데이터만 쿼리(Query)하여 가져감.
*   **장점**: BFF 팀이 모든 요구사항을 처리하던 병목 현상을 제거하고, 프런트엔드와 백엔드의 결합도를 낮춤.

---

## 3. 기술 스택 비교 요약

| 구분 | Classic (Rest-based) | Modern (GraphQL-based) |
| :--- | :--- | :--- |
| **핵심 프레임워크** | Zuul + RxJava | Spring Boot + DGS |
| **데이터 조합 주체** | BFF 서버 개발자 (코드 작성) | 클라이언트 (Query 작성) |
| **통신 방식** | REST / RPC | GraphQL |
| **장애 대응** | Hystrix / Resilience4j | GraphQL Error Handling + Gateway |
| **현재 상태** | 유지보수 위주 | 넷플릭스 내 표준 권장 |

---

## 4. 우리 프로젝트에 주는 시사점

1.  **비동기 처리의 중요성**: 넷플릭스가 RxJava로 해결했던 "병렬 API 호출 및 조합"은 현대의 **Spring WebFlux + Coroutines**와 목적이 동일합니다.
2.  **단계적 접근**: 넷플릭스 수준의 거대한 그래프(Federation)를 구축하기 전에는, **WebFlux 기반의 Aggregator형 BFF**로 시작하는 것이 기술적 부채를 최소화하는 길입니다.
3.  **Spring Ecosystem 활용**: 넷플릭스 기술 대부분이 Spring Cloud에 흡수되었으므로(Spring Cloud Gateway 등), 최신 Spring 표준을 따르는 것이 곧 넷플릭스의 검증된 방식을 따르는 것과 같습니다.
