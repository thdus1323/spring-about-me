package com.example.aboutme.counsel.CounselResponseRecord;

public record ReservationDetailsDTO(
        VoucherDTO voucher,
        UserDTO user
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

    public record UserDTO(
            Integer id,
            String level
    ) {
    }


}
