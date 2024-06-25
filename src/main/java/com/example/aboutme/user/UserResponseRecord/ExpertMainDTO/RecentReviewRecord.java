package com.example.aboutme.user.UserResponseRecord.ExpertMainDTO;

public record RecentReviewRecord(
        Integer reviewId,
        String clientName,
        Double score,
        String content
) {
}
