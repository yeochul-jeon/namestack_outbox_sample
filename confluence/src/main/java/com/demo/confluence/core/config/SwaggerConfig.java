package com.demo.confluence.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Confluence 챗봇 API")
                .description("챗봇 연동용 컨플루언스 문서 조회 및 검색 API")
                .version("1.0.0")
            );
    }

}
