package com.example.aboutme._core.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

//로그인할때 세션을 초기화 하기
//@Component
//public class RedisDataCleaner implements CommandLineRunner {
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    public RedisDataCleaner(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        redisTemplate.delete("sessionUser"); // 세션 키 삭제
//        System.out.println("Session user data has been cleared.");
//    }
//}