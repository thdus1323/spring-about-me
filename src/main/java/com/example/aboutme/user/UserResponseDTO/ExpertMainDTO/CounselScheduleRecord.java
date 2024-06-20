package com.example.aboutme.user.UserResponseDTO.ExpertMainDTO;

import com.example.aboutme.voucher.enums.VoucherType;

import java.time.LocalDateTime;

public record CounselScheduleRecord(
        Integer counselId,
        String clientName,
        LocalDateTime counselDate,
        VoucherType voucherType,
        Integer duration
) {

}
