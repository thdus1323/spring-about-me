package com.example.aboutme.user.UserResponseDTO.ExpertMainDTO;

public record RecentReviewRecord(
        Integer reviewId,
        String clientName,
        Double score,
        String content
) {
}
