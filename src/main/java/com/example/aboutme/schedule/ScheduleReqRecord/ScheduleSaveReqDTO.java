package com.example.aboutme.schedule.ScheduleReqRecord;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ScheduleSaveReqDTO(
        @JsonProperty("monday") ScheduleDTO monday,
        @JsonProperty("tuesday") ScheduleDTO tuesday,
        @JsonProperty("wednesday") ScheduleDTO wednesday,
        @JsonProperty("thursday") ScheduleDTO thursday,
        @JsonProperty("friday") ScheduleDTO friday
) {
    @JsonCreator
    public ScheduleSaveReqDTO {
    }

    public record ScheduleDTO(
            @JsonProperty("start") String start,
            @JsonProperty("end") String end
    ) {
        @JsonCreator
        public ScheduleDTO {
        }
    }
}