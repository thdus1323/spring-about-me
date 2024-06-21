package com.example.aboutme.reservation.resrvationResponse;

public record ReservationListDTO(
        Integer expertId,
        String startDate,
        String endDate,
        String startHour,
        String endHour,
        String restType,
        String dayOfWeek,
        String specificDate
) {
}
