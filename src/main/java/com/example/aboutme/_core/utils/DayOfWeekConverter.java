package com.example.aboutme._core.utils;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class DayOfWeekConverter {

    private static final Map<String, DayOfWeek> koreanToEnum = new HashMap<>();
    private static final Map<DayOfWeek, String> enumToKorean = new HashMap<>();

    static {
        koreanToEnum.put("일", DayOfWeek.SUNDAY);
        koreanToEnum.put("월", DayOfWeek.MONDAY);
        koreanToEnum.put("화", DayOfWeek.TUESDAY);
        koreanToEnum.put("수", DayOfWeek.WEDNESDAY);
        koreanToEnum.put("목", DayOfWeek.THURSDAY);
        koreanToEnum.put("금", DayOfWeek.FRIDAY);
        koreanToEnum.put("토", DayOfWeek.SATURDAY);

        enumToKorean.put(DayOfWeek.SUNDAY, "일");
        enumToKorean.put(DayOfWeek.MONDAY, "월");
        enumToKorean.put(DayOfWeek.TUESDAY, "화");
        enumToKorean.put(DayOfWeek.WEDNESDAY, "수");
        enumToKorean.put(DayOfWeek.THURSDAY, "목");
        enumToKorean.put(DayOfWeek.FRIDAY, "금");
        enumToKorean.put(DayOfWeek.SATURDAY, "토");
    }

    public static DayOfWeek toEnum(String koreanDay) {
        return koreanToEnum.get(koreanDay);
    }

    public static String toKorean(DayOfWeek dayOfWeek) {
        return enumToKorean.get(dayOfWeek);
    }
}

