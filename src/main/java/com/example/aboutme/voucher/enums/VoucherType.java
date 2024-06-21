package com.example.aboutme.voucher.enums;

public enum VoucherType {
    TEXT_THERAPY("텍스트 테라피"), // 텍스트 테라피
    VOICE_THERAPY("음성 테라피"), // 음성 테라피
    VIDEO_THERAPY("화상 테라피"); // 화상 테라피

    private final String voucherType;

    VoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherType() {
        return voucherType;
    }
}
