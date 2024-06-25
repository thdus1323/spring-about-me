package com.example.aboutme.user.UserResponseRecord.ClientMainDTO;

import java.util.List;

public record ExpertDTORecord(
        Integer expertId,
        String name,
        String profileImage,
        String expertTitle,
        List<VoucherDTORecord> vouchers,
        boolean hasTextTherapy,
        boolean hasVoiceTherapy,
        boolean hasVideoTherapy
) {}