package com.example.aboutme.user.UserRequestRecord;

import lombok.Builder;

import java.util.List;

public record ExpertSpecUpdateReqDTO(
        List<CareerReqDTO> career,
        List<EducationReqDTO> education

) {
    @Builder
    public record CareerReqDTO(
            Integer userId,
            String specType,
            Integer startYear,
            Integer endYear,
            String details

    ){}
    @Builder
    public record EducationReqDTO(
            Integer userId,
            String specType,
            Integer startYear,
            Integer endYear,
            String details
    )
    {}
}
