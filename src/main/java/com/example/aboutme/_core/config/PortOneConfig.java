package com.example.aboutme._core.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Getter
@Configuration
@EnableRedisHttpSession
public class PortOneConfig {

    @Value("${port.api.key}")
    private String apiKey;

    @Value("${port.api.secret}")
    private String apiSecret;

}
