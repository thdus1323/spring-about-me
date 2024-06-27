package com.example.aboutme.counsel.CounselRequestRecord;

public record PaymentPortOneReqDTO(
        double amount,
        String paymentMethod,
        Integer voucherId,
        String merchantUid,
        double price,
        Integer count,
        Integer duration

) {
}
