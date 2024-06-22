package com.example.aboutme.user.UserResponseDTO.ExpertMainDTO;

import com.example.aboutme._core.utils.Formatter;
import com.example.aboutme.voucher.enums.VoucherType;

import java.time.LocalDateTime;

public record CounselScheduleRecord(
        Integer counselId,
        String clientName,
        String counselDate,
        String voucherType,
        Integer duration
) {
    public CounselScheduleRecord(Integer counselId, String clientName, LocalDateTime counselDate, VoucherType voucherType, Integer duration) {
        this(counselId, clientName, Formatter.formatDate(counselDate), voucherType.getKorean(), duration);
    }
}