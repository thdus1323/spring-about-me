package com.example.aboutme.review.ReviewReqRecord;

import lombok.Builder;

@Builder
public record WriteReviewReqDTO(
        String reviewCountent,
        Integer expertId,
        Integer clientId,
        Integer counselId,
        String voucherType,
        String counselDate,
        String selectedRating,
        Integer reviewScore
) {
}
