package com.example.aboutme.reservation.RevervationRequest;

public record ReservationTempRepDTO(
        String reservationDate,
        String dayOfWeek,
        String startTime,
        Integer expertId,
        Integer voucherId
) {
}