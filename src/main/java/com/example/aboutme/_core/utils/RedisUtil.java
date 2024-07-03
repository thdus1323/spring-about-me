package com.example.aboutme._core.utils;

import com.example.aboutme.user.SessionUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SESSIONUSER = "sessionUser";

    public void saveSessionUser(SessionUser sessionUser) {
        try {
            String sessionUserJson = objectMapper.writeValueAsString(sessionUser);
            redisTemplate.opsForValue().set(SESSIONUSER, sessionUserJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Redis 저장 중 오류 발생: " + e.getMessage(), e);
        }
    }

    public void saveUserRole(String userRole) {
        try {
            String userRoleJson = objectMapper.writeValueAsString(userRole);
            redisTemplate.opsForValue().set("userRole", userRoleJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Redis 저장 중 오류 발생: " + e.getMessage(), e);
        }
    }

    public Object getUserRole() {
        return redisTemplate.opsForValue().get("userRole");
    }

    public SessionUser getSessionUser() {
        Object sessionUserJson = redisTemplate.opsForValue().get(SESSIONUSER);
        if (sessionUserJson != null) {
            try {
                SessionUser sessionUser = objectMapper.readValue(sessionUserJson.toString(), SessionUser.class);
                sessionUser.determineRoles(); // 역할 설정
                return sessionUser;
            } catch (JsonProcessingException e) {
                log.error("Error reading sessionUserJson: {}", e.getMessage());
                return null;
            }
        }
        return null;
    }
}
