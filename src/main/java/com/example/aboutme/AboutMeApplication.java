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

        SpringApplication.run(AboutMeApplication.class, args);
    }

}
