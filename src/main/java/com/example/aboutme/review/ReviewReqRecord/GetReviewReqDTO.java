package com.example.aboutme.review.ReviewReqRecord;

public record GetReviewReqDTO(
        Integer expertId,
        Integer clientId,
        Integer counselId,
        String voucherType,
        String counselDate
) {
}
