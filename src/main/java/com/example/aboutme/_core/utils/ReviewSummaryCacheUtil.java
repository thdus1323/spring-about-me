package com.example.aboutme._core.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class ReviewSummaryCacheUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${review.summary.cache.duration}")  // 설정 파일에서 값을 주입받습니다.
    private long cacheDurationMinutes;

    public String getSummary(Integer expertId) {
        return (String) redisTemplate.opsForValue().get(getCacheKey(expertId));
    }

    public void putSummary(Integer expertId, String summary) {
        redisTemplate.opsForValue().set(getCacheKey(expertId), summary, cacheDurationMinutes, TimeUnit.MINUTES);
    }

    private String getCacheKey(Integer expertId) {
        return "reviewSummary:" + expertId;
    }
}
