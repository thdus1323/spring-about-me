package com.example.aboutme.payment.PaymentResponseRecord;

public record PaymentDetailsDTO(
        VoucherDTO voucher,
        ExpertDTO user,
        Integer reservationId
) {

    public record VoucherDTO(
            Integer id,
            String voucherType,
            Integer expertId,
            String price,
            Integer count,
            Integer duration
    ) {
    }

    public record ExpertDTO(
            Integer id,
            String level
    ) {
    }


}
