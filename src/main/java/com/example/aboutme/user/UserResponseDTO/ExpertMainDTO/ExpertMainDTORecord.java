package com.example.aboutme.user.UserResponseDTO.ExpertMainDTO;

import java.util.List;

public record ExpertMainDTORecord(
        List<RecentReviewRecord> recentReviewRecords,
        List<CounselScheduleRecord> counselScheduleRecords
) {

}
