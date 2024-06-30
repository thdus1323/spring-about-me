package com.example.aboutme.user.UserResponseRecord;

import com.example.aboutme.user.enums.SpecType;
import lombok.Builder;

import java.util.List;

@Builder
public record ExpertFindDetailDTO(
        String lowestPrice,
        String reviewSummary,// 추가된 필드
        User user,
        Double averageScore,
        List<Review> reviews,
        List<PR> prs,
        List<Spec> careerSpecs,
        List<Spec> educationSpecs,
        List<ReviewCount> reviewCounts
) {

    @Builder
    public record User(
            Integer expertId,
            String name,
            String profileImage,
            String expertTitle,
            Integer totalReviews

    ) {
    }

    public record PR(
            Integer expertId,
            String intro,
            String effects,
            String methods
    ) {
    }

    @Builder
    public record Review(
            Integer reviewId,
            String content,
            String voucherType,
            Integer voucherCount,
            Integer reviewScore

    ) {
    }

    public record Spec(
            Integer userId,
            SpecType specType,
            String details
    ) {
    }

    @Builder
    public record ReviewCount(
            Integer score,
            Long count,
            Double percentage
    ) {
    }
}

