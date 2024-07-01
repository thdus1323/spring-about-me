package com.example.aboutme.counsel.CounselResponseRecord.CounselDTO;

public record UserRecord(
     Integer id,
     String name,
     String imagePath,
     String reservationStatus,
     Integer counselId,
     String voucherType,
     Integer totalCount,
     Integer remainCount,
     String applyDate,
     boolean isComplete
) {}
