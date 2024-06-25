package com.example.aboutme.user.UserResponseRecord.ExpertMainDTO;

import java.util.List;

public record ExpertMainDTORecord(
        List<RecentReviewRecord> recentReviewRecords,
        List<CounselScheduleRecord> counselScheduleRecords
) {

}
