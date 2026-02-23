package com.demo.confluence.core.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "confluence")
public class ConfluenceProperties {
    private Auth auth;
    private String baseUrl;
    private String spaceKey;


    @Setter
    @Getter
    public static class Auth {
        private String email;
        private String apiToken;

    }
}
