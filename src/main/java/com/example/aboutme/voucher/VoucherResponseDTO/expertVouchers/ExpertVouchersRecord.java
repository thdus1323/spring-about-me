package com.example.aboutme.voucher.VoucherResponseDTO.expertVouchers;

import java.util.List;

public record ExpertVouchersRecord(
        Integer expertId,
        String name,
        List<VouchersRecord> textVoucher,
        List<VouchersRecord> voiceVoucher,
        List<VouchersRecord> videoVoucher
) {
}
