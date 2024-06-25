package com.example.aboutme.counsel.CounselResponseDTO.CounselDTO;

import java.util.List;

public record CounselDTORecord(
        Integer expertId,
        String profileImage,
        List<UserRecord> user
) {}
