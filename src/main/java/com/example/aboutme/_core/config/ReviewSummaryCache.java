package com.example.aboutme._core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ReviewSummaryCache {

    private final ConcurrentHashMap<Integer, CacheEntry> cache = new ConcurrentHashMap<>();
    private final long cacheDurationMinutes;
    
    public ReviewSummaryCache(@Value("${review.summary.cache.duration}") long cacheDurationMinutes) {
        this.cacheDurationMinutes = cacheDurationMinutes;
    }

    public String getSummary(Integer expertId) {
        CacheEntry entry = cache.get(expertId);
        if (entry == null || isExpired(entry)) {
            return null;
        }
        return entry.getSummary();
    }

    public void putSummary(Integer expertId, String summary) {
        cache.put(expertId, new CacheEntry(summary, LocalDateTime.now()));
    }

    private boolean isExpired(CacheEntry entry) {
        return entry.getTimestamp().plusMinutes(cacheDurationMinutes).isBefore(LocalDateTime.now());
    }

    private static class CacheEntry {
        private final String summary;
        private final LocalDateTime timestamp;

        public CacheEntry(String summary, LocalDateTime timestamp) {
            this.summary = summary;
            this.timestamp = timestamp;
        }

        public String getSummary() {
            return summary;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }
}