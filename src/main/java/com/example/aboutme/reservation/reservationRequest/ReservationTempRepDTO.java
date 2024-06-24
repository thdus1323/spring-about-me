package com.example.aboutme.reservation.reservationRequest;

public record ReservationTempRepDTO(
        String reservationDate,
        String dayOfWeek,
        String startTime,
        Integer expertId,
        Integer voucherId
) {
}