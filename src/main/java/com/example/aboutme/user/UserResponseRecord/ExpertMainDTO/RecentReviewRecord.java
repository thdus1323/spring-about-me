package com.example.aboutme.user.UserResponseRecord.ExpertMainDTO;

public record RecentReviewRecord(
        Integer reviewId,
        String clientName,
        Integer score,
        String content
) {
}
