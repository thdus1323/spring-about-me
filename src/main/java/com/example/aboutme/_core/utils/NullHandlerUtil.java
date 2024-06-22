package com.example.aboutme._core.utils;

import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.enums.DayOfWeek;
import com.example.aboutme.schedule.enums.RestType;
import com.example.aboutme.user.User;

import java.time.LocalDate;
import java.time.LocalTime;

public class NullHandlerUtil {

    public static Schedule handleNulls(Schedule schedule) {
        if (schedule.getStartTime() == null) {
            schedule.setStartTime(LocalTime.of(9, 0));
        }
        if (schedule.getEndTime() == null) {
            schedule.setEndTime(LocalTime.of(18, 0));
        }
        if (schedule.getRestType() == null) {
            schedule.setRestType(RestType.PERIODIC);
        }
        if (schedule.getStartDay() == null) {
            schedule.setStartDay(DayOfWeek.MONDAY);
        }
        if (schedule.getEndDay() == null) {
            schedule.setEndDay(DayOfWeek.FRIDAY);
        }
        if (schedule.getSpecificDate() == null) {
            schedule.setSpecificDate(LocalDate.of(1970, 1, 1));
        }
        if (schedule.getLunchStartTime() == null) {
            schedule.setLunchStartTime(LocalTime.of(12, 0));
        }
        if (schedule.getLunchEndTime() == null) {
            schedule.setLunchEndTime(LocalTime.of(13, 0));
        }
        if (schedule.getNotes() == null) {
            schedule.setNotes("");
        }
        return schedule;
    }

    // User 기본값 설정
    public static User handleNulls(User user) {
        if (user.getProfileImage() == null || user.getProfileImage().isEmpty()) {
            user.setProfileImage("/images/badge.png");
        }
        if (user.getExpertTitle() == null || user.getExpertTitle().isEmpty()) {
            user.setExpertTitle("당신의 길에 함께하는 동반자가 되고 싶습니다.");
        }
        // Add more fields as needed
        return user;
    }


}
