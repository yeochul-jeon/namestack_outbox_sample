# Confluence API 연동 프로젝트

이 문서는 코파일럿 스튜디오 챗봇에 사내 컨플루언스 공간의 내용을 제공하기 위한 API 프로젝트의 개발 과정을 설명합니다.

## 1. 프로젝트 개요

- **목표**: 사내 컨플루언스 문서 내용을 조회하는 REST API를 개발하여 챗봇이 활용할 수 있도록 합니다.
- **기술 스택**: Spring Boot, Java 21, Gradle, WebClient

## 2. 개발 단계

### 2.1. WebClient 의존성 추가

컨플루언스 REST API 호출을 위해 Spring WebFlux의 `WebClient`를 사용합니다. `build.gradle` 파일에 다음 의존성을 추가합니다.

```gradle
implementation 'org.springframework.boot:spring-boot-starter-webflux'
```

### 2.2. Confluence 설정 속성 클래스 생성

`application.yml`에 정의된 컨플루언스 관련 설정을 Java 객체로 바인딩하기 위한 `@ConfigurationProperties` 클래스를 생성합니다.

`src/main/java/com/demo/confluence/core/config/ConfluenceProperties.java` 파일을 생성하고 다음 내용을 추가합니다.

```java
package com.demo.confluence.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "confluence")
public class ConfluenceProperties {
    private Auth auth;
    private String baseUrl;

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static class Auth {
        private String email;
        private String apiToken;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getApiToken() {
            return apiToken;
        }

        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }
    }
}
```

### 2.3. Confluence API 서비스 구현

컨플루언스 REST API를 호출하고 응답을 처리하는 서비스 클래스를 구현합니다. 여기서는 문서 검색 기능을 예시로 구현합니다.

`src/main/java/com/demo/confluence/service/ConfluenceService.java` 파일을 생성하고 다음 내용을 추가합니다.

```java
package com.demo.confluence.service;

import com.demo.confluence.core.config.ConfluenceProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

@Slf4j
@Service
public class ConfluenceService {

    private final WebClient webClient;
    private final ConfluenceProperties confluenceProperties;

    public ConfluenceService(WebClient.Builder webClientBuilder, ConfluenceProperties confluenceProperties) {
        this.webClient = webClientBuilder.baseUrl(confluenceProperties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + confluenceProperties.getAuth().getApiToken())
                .defaultHeader(HttpHeaders.ACCEPT, "application/json")
                .build();
    }

    public Mono<String> searchContent(String query, String spaceKey) {
        String escapedQuery = query.replace("\"", "\\\""); // Escape double quotes within the query
        String cql = "text ~ \"" + escapedQuery + "\"";
        if (spaceKey != null && !spaceKey.isEmpty()) {
            cql += " AND space = \"" + spaceKey + "\"";
        }

        String finalCql = cql;
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rest/api/content/search")
                        .queryParam("cql", finalCql)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getSpaceContent(String spaceKey) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rest/api/space/{spaceKey}/content")
                        .build(spaceKey))
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(e -> log.error("TASK 오류 발생: {}", e.getMessage(), e));
    }

    public Mono<String> getSpaceContentWithExpansion(String spaceKey) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rest/api/space/{spaceKey}/content")
                        .queryParam("expand", "body,storage")
                        .build(spaceKey))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getPageContentWithExpansion(String pageId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/rest/api/content/{pageId}")
                        .queryParam("expand", "body,storage,descendants")
                        .build(pageId))
                .retrieve()
                .bodyToMono(String.class);
    }
}
```

### 2.4. Confluence API 컨트롤러 구현

외부에서 호출할 수 있는 REST API 엔드포인트를 정의합니다. 이 컨트롤러는 `ConfluenceService`를 사용하여 컨플루언스에서 데이터를 가져옵니다.

`src/main/java/com/demo/confluence/controller/ConfluenceController.java` 파일을 생성하고 다음 내용을 추가합니다.

```java
package com.demo.confluence.controller;

import com.demo.confluence.service.ConfluenceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/confluence")
public class ConfluenceController {

    private final ConfluenceService confluenceService;

    public ConfluenceController(ConfluenceService confluenceService) {
        this.confluenceService = confluenceService;
    }

    @GetMapping("/search")
    public Mono<String> searchConfluence(@RequestParam String query,
                                         @RequestParam(required = false) String spaceKey) {
        return confluenceService.searchContent(query, spaceKey);
    }

    @GetMapping("/space/{spaceKey}/content")
    public Mono<String> getSpaceContent(@PathVariable String spaceKey) {
        return confluenceService.getSpaceContent(spaceKey);
    }

    @GetMapping("/space/{spaceKey}/content-expanded")
    public Mono<String> getSpaceContentExpanded(@PathVariable String spaceKey) {
        return confluenceService.getSpaceContentWithExpansion(spaceKey);
    }

    @GetMapping("/page/{pageId}/content-expanded") // New endpoint
    public Mono<String> getPageContentExpanded(@PathVariable String pageId) {
        return confluenceService.getPageContentWithExpansion(pageId);
    }
}
```

### 2.5. `application.yml` 설정 업데이트

`src/main/resources/application.yml` 파일에서 컨플루언스 관련 설정을 주석 해제하고 실제 값으로 채워야 합니다.

```yaml
confluence:
  auth:
    email: your_confluence_email@example.com # 실제 컨플루언스 계정 이메일
    apiToken: your_confluence_api_token # 실제 컨플루언스 API 토큰
  baseUrl: https://your-confluence-instance.com/wiki # 실제 컨플루언스 인스턴스 URL
```

**주의**: `your_confluence_email@example.com`과 `your_confluence_api_token`, `https://your-confluence-instance.com/wiki`는 실제 컨플루언스 환경에 맞게 변경해야 합니다. API 토큰은 컨플루언스 개인 설정에서 생성할 수 있습니다. `baseUrl`은 컨플루언스 인스턴스의 기본 URL이며, `/wiki` 경로가 포함될 수 있습니다.

## 3. 빌드 및 실행

모든 파일 수정 후, 프로젝트를 빌드하고 실행합니다.

```bash
./gradlew clean build
java -jar build/libs/confluence-0.0.1-SNAPSHOT.jar
```

또는 IDE에서 Spring Boot 애플리케이션을 실행합니다.

## 4. API 테스트

애플리케이션이 실행되면 다음 URL로 API를 테스트할 수 있습니다.

- **콘텐츠 검색 (쿼리 및 선택적 공간 키 포함):**
  `http://localhost:8080/api/confluence/search?query=테스트`
  `http://localhost:8080/api/confluence/search?query=테스트&spaceKey=ENMcommercesystem`
  `query` 파라미터에 검색하고자 하는 키워드를 입력하고, `spaceKey` 파라미터에 특정 공간 키를 입력하여 컨플루언스 문서를 검색할 수 있습니다.

- **특정 공간의 모든 콘텐츠 조회:**
  `http://localhost:8080/api/confluence/space/ENMcommercesystem/content`
  `ENMcommercesystem` 부분을 조회하고자 하는 공간 키로 변경하여 사용합니다.

- **특정 공간의 모든 콘텐츠 조회 (페이지 내용 포함):**
  `http://localhost:8080/api/confluence/space/ENMcommercesystem/content-expanded`
  `ENMcommercesystem` 부분을 조회하고자 하는 공간 키로 변경하여 사용합니다. 이 엔드포인트는 페이지의 `body` 및 `storage` 내용을 확장하여 반환합니다.

- **특정 페이지의 상세 내용 조회 (페이지 내용 및 하위 항목 포함):**
  `http://localhost:8080/api/confluence/page/101642495/content-expanded`
  `101642495` 부분을 조회하고자 하는 페이지 ID로 변경하여 사용합니다. 이 엔드포인트는 페이지의 `body`, `storage` 및 `descendants` 내용을 확장하여 반환합니다.

## 5. 다음 단계

- 컨플루언스 API 응답을 파싱하여 필요한 정보만 추출하고, 챗봇에 적합한 형태로 가공합니다.
- 인증 방식 강화 (예: OAuth2).
- 에러 핸들링 및 로깅 추가.
- 특정 공간 또는 페이지 ID로 검색하는 기능 추가.
- 컨플루언스 v2 API 활용 검토.
