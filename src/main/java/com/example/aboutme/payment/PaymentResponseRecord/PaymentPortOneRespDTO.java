package com.example.aboutme.payment.PaymentResponseRecord;

public record PaymentPortOneRespDTO(
        Integer id,
        String impUid,
        String merchantUid,
        double amount,
        String paymentMethod,
        String buyerName,
        String buyerTel,
        String status
) {
}