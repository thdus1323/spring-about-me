package com.example.aboutme.reservation.reservationRequest;

public record ReservationRepDTO(
        String reservationDate,
        String dayOfWeek,
        String startTime,
        Integer expertId,
        Integer voucherId
) {
}