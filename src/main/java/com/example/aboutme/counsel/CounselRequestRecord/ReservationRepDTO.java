package com.example.aboutme.counsel.CounselRequestRecord;

public record ReservationRepDTO(
        String reservationDate,
        String dayOfWeek,
        String startTime,
        Integer expertId,
        Integer voucherId,
        Integer paymentId
) {
}