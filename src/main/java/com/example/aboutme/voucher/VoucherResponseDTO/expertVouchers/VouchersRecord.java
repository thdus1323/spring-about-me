package com.example.aboutme.voucher.VoucherResponseDTO.expertVouchers;

import com.example.aboutme.voucher.enums.VoucherType;

public record VouchersRecord(
        Integer voucherId,
        VoucherType voucherType,
        Integer count,
        Integer duration,
        String price
) {
}
