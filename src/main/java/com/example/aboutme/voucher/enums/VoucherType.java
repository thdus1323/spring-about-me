package com.example.aboutme.voucher.enums;

import com.example.aboutme._core.error.exception.Exception400;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum VoucherType {
    TEXT_THERAPY("텍스트 테라피"), // 텍스트 테라피
    VOICE_THERAPY("보이스 테라피"), // 음성 테라피
    VIDEO_THERAPY("화상 테라피"); // 화상 테라피

    private final String korean;

    VoucherType(String korean) {
        this.korean = korean;
    }

    @JsonCreator
    public static VoucherType fromKorean(String korean) {
        for (VoucherType voucherType : VoucherType.values()) {
            if (voucherType.korean.equals(korean)) {
                return voucherType;
            }
        }
        throw new Exception400("존재하지 않는 바우처 입니다." + korean);
    }

    public String getKorean() {
        return korean;
    }
}
