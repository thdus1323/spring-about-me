package com.example.aboutme.payment.PaymentRequestRecord;

public record PaymentPortOneReqDTO(
        double amount,
        String paymentMethod,
        Integer voucherId,
        String merchantUid

) {
}
