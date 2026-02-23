package com.example.bff.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * WebClient 설정
 * - K8s Service Discovery를 위한 LoadBalancer 활성화
 * - 타임아웃 설정 (Connection, Read, Write)
 * - 기본 헤더 설정
 */
@Configuration
public class WebClientConfiguration {

    /**
     * WebClient.Builder 빈 설정
     * - K8s Service Discovery 활성화
     * - 타임아웃 설정
     */
    @Bean
//    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        HttpClient httpClient = HttpClient.create()
            // Connection Timeout: 5초
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            // Response Timeout: 10초
            .responseTimeout(Duration.ofSeconds(10))
            // Read/Write Timeout Handler 추가
            .doOnConnected(conn -> {
                conn.addHandlerLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS));
                conn.addHandlerLast(new WriteTimeoutHandler(10, TimeUnit.SECONDS));
            });

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    }

    /**
     * 기본 WebClient 빈
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
