package com.example.aboutme.counsel.CounselRequestRecord;

public record CounselReqDTO(
        Integer userId,
        String voucherType,
        String reservationDate,
        String startTime
) {
}
