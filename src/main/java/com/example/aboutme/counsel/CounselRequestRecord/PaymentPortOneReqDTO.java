package com.example.aboutme.counsel.CounselRequestRecord;

public record PaymentPortOneReqDTO(
        String impUid,
        double amount,
        String paymentMethod,
        Integer voucherId,
        String merchantUid,
        double price,
        Integer voucherCount,
        String voucherType,
        Integer duration

) {
}
