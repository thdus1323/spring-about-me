package com.example.aboutme.user.UserResponseRecord;

import com.example.aboutme.user.enums.UserRole;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.List;

@Builder
public record UserProfileDTO(
        User user,
        List<PaymentDTO> payments,
        List<VoucherDTO> vouchers,  // 새로운 필드 추가
        List<ReservationDTO> progressReservations,
        List<CounselDTO> completedCounsels,
        Page<Comm> commPosts,
        Page<Reply> replies,
        Page<Review> reviews

) {
    @Builder
    public record User(
            Integer id,
            UserRole userRole,
            String name,
            String email,
            String birth,
            String gender,
            String profileImage
    ) {
    }

    @Builder
    public record PaymentDTO(
            Integer id,
            String paymentMethod,
            String amount,
            String paymentDate,
            Integer voucherCount,
            Integer voucherDuration,
            String voucherType,
            String createdAt,
            String updatedAt
    ) {

    }

    @Builder
    public record VoucherDTO(
            Integer id,
            String voucherType,
            Integer clientId,
            Integer expertId,
            Integer voucherId,
            Integer paymentId,
            String price,
            Integer count,
            Integer remainingCount,
            Integer counselCount,
            Integer duration,
            String createdAt,
            String updatedAt,
            boolean isRemainingCount
    ) {
    }

    @Builder
    public record ReservationDTO(
            Integer id,
            Integer expertId,
            Integer clientId,
            Integer voucherId,
            Integer scheduleId,
            String status,
            String startTime,
            String reservationDate,
            String dayOfWeek,
            Timestamp createdAt,
            Timestamp updatedAt,
            String voucherType,
            Integer reservationCount,
            Integer usedCount,
            Integer voucherCount
    ) {
    }

    @Builder
    public record CounselDTO(
            Integer id,
            Integer expertId,
            Integer clientId,
            Integer voucherId,
            String counselDate,
            String result,
            String state,
            String createdAt,
            String updatedAt,
            String voucherType,
            Integer useCount,
            Integer voucherCount,
            boolean isReview
    ) {
    }

    @Builder
    public record Comm(
            Integer id,
            String name,
            String content,
            String title,
            String category,
            String profileImage

    ) {
    }

    @Builder
    public record Reply(
            Integer id,
            Integer userId,
            Integer commId,
            String content,
            String category,
            String profileImage,
            String name
    ) {
    }

    @Builder
    public record Review(
            Integer id,
            Integer clientId,
            Integer expertId,
            Integer counselId,
            Integer score,
            String content,
            String voucherType,
            Integer voucherCont,
            String expertName,
            String nickName,
            String profileImage


    ) {
    }
}
