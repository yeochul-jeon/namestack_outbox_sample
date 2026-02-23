package com.demo.confluence.service;

import com.demo.confluence.core.config.ConfluenceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ConfluenceService {

    private final WebClient webClient;
    private final ConfluenceProperties properties;

    public ConfluenceService(WebClient.Builder webClientBuilder,
        ConfluenceProperties confluenceProperties, ConfluenceProperties properties) {

        this.properties = confluenceProperties;
        this.webClient = webClientBuilder.baseUrl(confluenceProperties.getBaseUrl())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .defaultHeader(HttpHeaders.AUTHORIZATION,
                "Bearer " + confluenceProperties.getAuth().getApiToken())
            .defaultHeader(HttpHeaders.ACCEPT, "application/json")
            .build();
    }

    public Mono<String> searchContent(String query) {
        String escapedQuery = query.replace("\"", "\\\""); // Escape double quotes within the query
        String cql = "text ~ \"" + escapedQuery + "\"";

        String spaceKey = properties.getSpaceKey();
        cql += " AND space=" + spaceKey;
//        cql += "&expand=body.storage";

        String finalCql = cql;
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/rest/api/content/search")
                .queryParam("cql", finalCql)
                .build())
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(e -> log.error("페이지 조회 오류 발생: {}", e.getMessage(), e))
            ;
    }

    public Mono<String> getSpaceContent(String spaceKey) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/rest/api/space/{spaceKey}/content")
                .build(spaceKey))
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(e -> log.error("공간내 페이지 조회 오류 발생: {}", e.getMessage(), e))
            ;
    }

    public Mono<String> getSpaceContentWithExpansion(String spaceKey) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/rest/api/space/{spaceKey}/content")
                .queryParam("expand", "body,storage")
                .build(spaceKey))
            .retrieve()
            .bodyToMono(String.class)
            .doOnError(e -> log.error("공간내 페이지 조회 오류 발생: {}", e.getMessage(), e))
            ;
    }

    public Mono<String> getPageContentWithExpansion(String pageId) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/rest/api/content/{pageId}")
                .queryParam("expand", "body,storage,version,ancestors,descendants")
                .build(pageId))
            .retrieve()
            .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                response -> response.bodyToMono(String.class)
                    .doOnNext(body -> log.error("API 에러 응답: {}", body))
                    .then(Mono.error(new RuntimeException("Confluence API 호출 실패: " + response.statusCode()))))
            .bodyToMono(String.class)
            .doOnError(error -> log.error("페이지 조회 중 오류 발생 - pageId: {}, error: {}", pageId, error.getMessage()))
            .doOnNext(response -> log.info("페이지 조회 성공 - pageId: {}", pageId))
            ;
    }
}

