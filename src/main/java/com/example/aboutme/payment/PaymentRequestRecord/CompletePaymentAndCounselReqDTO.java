package com.example.aboutme.payment.PaymentRequestRecord;

public record CompletePaymentAndCounselReqDTO(
        Integer voucherId,
        Integer expertId,
        String merchantUid,
        String impUid,
        Integer reservationId,
        String reservationDate,
        String reservationTime
) {}
