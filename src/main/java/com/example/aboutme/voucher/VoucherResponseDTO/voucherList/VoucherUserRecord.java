package com.example.aboutme.voucher.VoucherResponseDTO.voucherList;

import com.example.aboutme.user.enums.ExpertLevel;

public record VoucherUserRecord(
        Integer expertId,
        String name,
        String level,
        String profileImage
) {}
