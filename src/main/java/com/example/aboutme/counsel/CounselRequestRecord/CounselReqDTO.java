package com.example.aboutme.counsel.CounselRequestRecord;

public record CounselReqDTO(
        Integer counselId,
        Integer userId,
        String voucherType,
        String reservationDate,
        String startTime
) {
}
