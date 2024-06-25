package com.example.aboutme.user.UserResponseRecord.ClientMainDTO;

import com.example.aboutme.voucher.enums.VoucherType;

public record VoucherDTORecord(
        VoucherType voucherType,
        Double price,
        Integer duration
) {}