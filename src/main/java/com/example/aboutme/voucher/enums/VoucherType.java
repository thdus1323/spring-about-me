package com.example.aboutme.voucher.enums;

public enum VoucherType {
    TEXT_THERAPY("텍스트 테라피"), // 텍스트 테라피
    VOICE_THERAPY("음성 테라피"), // 음성 테라피
    VIDEO_THERAPY("화상 테라피"); // 화상 테라피

    private final String korean;

    VoucherType(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
