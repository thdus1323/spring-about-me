package com.example.aboutme.user.UserResponseRecord.ClientMainDTO;

public record CommDTORecord(
        Integer communityId,
        String writerName,
        String writerImage,
        String expertName,
        String expertImage,
        String title,
        String content,
        String category
) {}
