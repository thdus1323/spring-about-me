package com.example.aboutme._core.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Getter
@Configuration
@EnableRedisHttpSession
public class OpenAIConfig {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.organization.id}")
    private String organizationId;

    @Value("${openai.project.id}")
    private String projectId;

}
