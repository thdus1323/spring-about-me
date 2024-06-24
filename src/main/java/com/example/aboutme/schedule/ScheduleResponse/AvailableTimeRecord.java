package com.example.aboutme.schedule.ScheduleResponse;

import java.time.LocalTime;

public record AvailableTimeRecord(LocalTime time, boolean isAvailable) {}