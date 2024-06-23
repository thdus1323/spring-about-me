package com.example.aboutme._core.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean
    public static Logger logger() {
        return LogManager.getLogger("GlobalLogger");
    }
}
