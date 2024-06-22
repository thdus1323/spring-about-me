package com.example.aboutme.reservation.resrvationResponse.ReservationDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record ReservationDetailsDTO(
        VoucherDTO voucher,
        List<ScheduleDTO> schedules,
        List<ReservationDTO> reservations
) {
    public record ReservationDTO(
            Integer id,
            Integer expertId,
            Integer clientId,
            Integer voucherId,
            String status,
            LocalTime startTime,
            LocalDate reservationDate,
            Integer scheduleId
    ) {
    }

    public record ScheduleDTO(
            Integer id,
            Integer expertId,
            LocalTime startTime,
            LocalTime endTime,
            String restType,
            String startDay,
            String endDay,
            LocalDate specificDate,
            LocalTime lunchStartTime,
            LocalTime lunchEndTime
    ) {
    }

    public record VoucherDTO(
            Integer id,
            String voucherType,
            Integer expertId,
            String price,
            Integer count,
            Integer duration,
            String imagePath,
            LocalDateTime startDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {

    }

}
