package com.example.aboutme.user.UserResponseRecord;

import com.example.aboutme.user.enums.UserRole;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

public record UserProfileDTO(
        User user,
        List<PaymentDTO> payments,
        List<ReservationDTO> progressReservations,
        List<CounselDTO> completedCounsels,
        List<Comm> CommPosts
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
            String voucherType,
            Integer clientId,
            Integer expertId,
            Integer voucherId,
            String paymentMethod,
            String price,
            Integer count,
            Integer remainingCount,  // 추가된 필드
            Integer counselCount,  // 추가된 필드
            Integer duration,
            String createdAt,
            String updatedAt,
            String paymentDate,
            String amount
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
            Integer reservationCount,  // 추가된 필드
            Integer usedCount,  // 추가된 필드
            Integer voucherCount  // 추가된 필드
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
            Integer voucherCount  // 추가된 필드
    ) {
    }

    @Builder
    public record Comm(
            Integer id,
            String name,
            String content,
            String title,
            String category
    ) {
    }
}
