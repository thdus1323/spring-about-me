package com.example.aboutme.user.UserResponseRecord;

import com.example.aboutme.user.enums.SpecType;
import com.example.aboutme.user.enums.UserRole;
import lombok.Builder;

import java.util.List;

public record ExpertUserProfileDTO(
        User user,
        List<SpecDTO> userSpeclist,
        List<ScheduleDTO> schedules

) {

    @Builder
    public record ScheduleDTO(
            Integer id,
            String dayOfWeek,
            String startTime,
            String endTime
    ) {
    }

    @Builder
    public record User(
            Integer id,
            UserRole userRole,
            String name,
            String email,
            String birth,
            String gender,
            String profileImage,
            String expertLevel
    ) {
    }


    @Builder
    public record SpecDTO(
            List<CareerDTO> career,
            List<EducationDTO> education

    ) {
        @Builder
        public record CareerDTO(
                Integer id,
                Integer userId,
                SpecType specType,
                String details,
                String startYear,
                String endYear
        ) {
        }

        @Builder
        public record EducationDTO(
                Integer id,
                Integer userId,
                SpecType specType,
                String details,
                String startYear,
                String endYear
        ) {
        }
    }
}
