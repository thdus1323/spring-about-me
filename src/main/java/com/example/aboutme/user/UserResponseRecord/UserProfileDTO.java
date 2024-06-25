package com.example.aboutme.user.UserResponseRecord;

import com.example.aboutme.user.enums.UserRole;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

public record UserProfileDTO(
        User user,
        List<VoucherDTO> vouchers,
        List<ReservationDTO> reservations,
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

    public record VoucherDTO(
            Integer id,
            String voucherType,
            Integer expertId,
            String price,
            Integer count,
            Integer duration,
            Timestamp createdAt,
            Timestamp updatedAt
    ) {
    }

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
            Timestamp updatedAt
    ) {
    }

    public record Comm(
            Integer id,
            String name,
            String content,
            String title,
            String category
    ) {
    }

}
