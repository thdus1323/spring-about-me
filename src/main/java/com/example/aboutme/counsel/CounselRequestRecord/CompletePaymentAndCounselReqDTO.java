package com.example.aboutme.counsel.CounselRequestRecord;

public record CompletePaymentAndCounselReqDTO(
        Integer voucherId,
        Integer expertId,
        String merchantUid,
        String impUid,
        Integer reservationId,
        String reservationDate,
        String reservationTime
) {
}
