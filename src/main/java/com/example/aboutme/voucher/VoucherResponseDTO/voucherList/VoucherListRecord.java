package com.example.aboutme.voucher.VoucherResponseDTO.voucherList;

import java.util.List;

public record VoucherListRecord(
        String lowestPrice,
        VoucherUserRecord user,
        List<VoucherRecord> textVoucher,
        List<VoucherRecord> voiceVoucher,
        List<VoucherRecord> videoVoucher
//        List<Voucher> psychVoucher
) {
}
