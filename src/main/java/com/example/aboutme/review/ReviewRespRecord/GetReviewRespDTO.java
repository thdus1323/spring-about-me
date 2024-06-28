package com.example.aboutme.review.ReviewRespRecord;

import lombok.Builder;

@Builder
public record GetReviewRespDTO(
        String profileImage,
        String expertName,
        String voucherType,
        String counselDate,
        Integer expertId,
        Integer clientId,
        Integer counselId
) {
}
