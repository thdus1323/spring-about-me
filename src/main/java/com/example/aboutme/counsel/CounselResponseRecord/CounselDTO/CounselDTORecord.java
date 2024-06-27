package com.example.aboutme.counsel.CounselResponseRecord.CounselDTO;

import java.util.List;

public record CounselDTORecord(
        Integer expertId,
        String profileImage,
        List<UserRecord> user
) {}
