package com.example.aboutme.counsel.CounselResponseRecord.CounselDTO;

import java.util.List;

public record CounselDTORecord(
        Integer expertId,

        List<UserRecord> user
) {}
