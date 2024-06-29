package com.example.aboutme.review.ReviewRespRecord;

import lombok.Builder;

@Builder
public record WriteReviewRespDTO(
        String reviewCountent,
        Integer expertId,
        Integer clientId,
        Integer counselId,
        String voucherType,
        String counselDate,
        String reviewScore
) {
}
