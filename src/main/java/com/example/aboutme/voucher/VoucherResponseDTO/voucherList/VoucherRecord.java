package com.example.aboutme.voucher.VoucherResponseDTO.voucherList;

import com.example.aboutme.voucher.enums.VoucherType;

public record VoucherRecord(
        VoucherType voucherType,
        Integer voucherId,
        String price,
        Integer count,
        Integer duration,
        String imagePath
) {}
