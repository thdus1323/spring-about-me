package com.example.aboutme.payment.PaymentResponseRecord;

import lombok.Builder;

public record PaymentDetailsDTO(
        VoucherDTO voucher,
        ExpertDTO user,
        ReservationDTO reservation

) {
    @Builder
    public record ReservationDTO(
            Integer reservationId,
            String reservationDate,
            String reservationTime
    ){}
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
