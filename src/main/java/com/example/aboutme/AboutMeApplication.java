package com.example.aboutme;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AboutMeApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        // Set environment variables
        System.setProperty("REDIS_HOST", dotenv.get("REDIS_HOST"));
        System.setProperty("REDIS_PORT", dotenv.get("REDIS_PORT"));
        System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));
        System.setProperty("OPENAI_ORGANIZATION_ID", dotenv.get("OPENAI_ORGANIZATION_ID"));
        System.setProperty("OPENAI_PROJECT_ID", dotenv.get("OPENAI_PROJECT_ID"));

        SpringApplication.run(AboutMeApplication.class, args);
    }

}
