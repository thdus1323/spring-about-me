package com.example.aboutme.user.record.expertFindRecord;

import java.util.List;

public record ExpertInfoRecord(
        Integer expertId,
        String name,
        String title,
        String profileImage,
        List<VoucherImageRecord> images
) {}
