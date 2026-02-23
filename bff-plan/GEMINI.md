# BFF Architecture Planning Context

이 디렉토리는 마이크로서비스 아키텍처(MSA) 환경에서의 **BFF (Backend for Frontend)** 도입을 위한 조사 및 설계 자료를 포함하고 있습니다.
주로 **Spring Ecosystem (Spring Cloud Gateway, Spring WebFlux)** 기반의 구현 전략과 국내 주요 기업(카카오페이, 우아한형제들 등)의 사례를 분석합니다.

## 📂 디렉토리 개요

이 프로젝트는 아직 실제 구현 코드가 포함되지 않은 **아키텍처 설계 및 기술 조사 단계**입니다.
핵심 목표는 프로젝트의 요구사항에 맞는 최적의 BFF 패턴(단순 라우팅 vs 데이터 조합)을 선정하는 것입니다.

### 핵심 문서

*   **`KakaoPay_Style_WebFlux_BFF.md`** (권장)
    *   **내용**: Spring Cloud Gateway를 사용하지 않고, **Spring WebFlux + Kotlin Coroutines**를 사용하여 직접 구현하는 'Aggregator' 패턴의 BFF 가이드입니다.
    *   **특징**: 단순 프록시가 아닌, 여러 마이크로서비스의 데이터를 병렬로 호출하여 조합(Aggregation)하는 로직에 최적화되어 있습니다.
    *   **기술 스택**: Kotlin, Spring Boot 3.x (WebFlux), WebClient, Coroutines.

*   **`BFF_Architecture_Research.md`**
    *   **내용**: Spring Cloud Gateway(SCG)를 활용한 표준적인 BFF 접근 방식과 국내외 기업(우아한형제들, NHN, Netflix 등)의 사례 연구입니다.
    *   **특징**: 인증/인가, 라우팅, 회복 탄력성(Resilience) 등 Gateway 본연의 역할에 집중합니다.

## 🚀 활용 가이드

이 문맥에서 AI 어시스턴트와 상호작용할 때는 다음 사항을 참고하세요:

1.  **아키텍처 결정 지원**: 두 문서(`SCG` vs `Pure WebFlux`)의 장단점을 비교하여, 현재 프로젝트 요건에 맞는 방식을 제안해달라고 요청할 수 있습니다.
2.  **프로토타이핑**: `KakaoPay_Style_WebFlux_BFF.md`에 있는 코드 스니펫을 바탕으로 실제 Kotlin/Spring Boot 프로젝트 구조를 생성해달라고 요청할 수 있습니다.
3.  **환경 설정**: 현재 `.tool-versions`에는 `nodejs`만 명시되어 있으나, 문서상으로는 **JDK 17 이상** 및 **Kotlin** 환경이 필요함을 인지해야 합니다.

## 🛠️ 기술 스택 (Planned)

문서 분석 결과, 향후 개발 시 예상되는 기술 스택은 다음과 같습니다:

*   **Language**: Kotlin (Primary), Java
*   **Framework**: Spring Boot 3.x (WebFlux)
*   **Async**: Kotlin Coroutines, Project Reactor
*   **Gateway**: Spring Cloud Gateway (Optional)
