package com.example.aboutme.voucher.VoucherRequestDTO;

import com.example.aboutme.voucher.enums.VoucherType;

import java.sql.Timestamp;

public record VoucherSaveDTO(
        Integer expertId,
        VoucherType voucherType,
        Integer count,
        Integer duration,
        double price
) {
}
