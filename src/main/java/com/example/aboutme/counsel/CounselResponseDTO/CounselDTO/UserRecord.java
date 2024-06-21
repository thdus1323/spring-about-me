package com.example.aboutme.counsel.CounselResponseDTO.CounselDTO;

import com.example.aboutme.voucher.enums.VoucherType;

public record UserRecord(
     Integer id,
     String name,
     String imagePath,
     String voucherType,
     Integer totalCount,
     Integer remainCount,
     String applyDate
) {}
