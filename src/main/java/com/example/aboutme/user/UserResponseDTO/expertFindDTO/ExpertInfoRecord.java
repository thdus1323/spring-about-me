package com.example.aboutme.user.UserResponseDTO.expertFindDTO;

import java.util.List;

public record ExpertInfoRecord(
        Integer expertId,
        String name,
        String title,
        String profileImage,
        List<VoucherImageRecord> images
) {}
