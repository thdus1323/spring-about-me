package com.example.aboutme._core.utils;


public class TimeUtil {

//    public static List<LocalTime> getUnavailableTimes(List<Schedule> schedules, List<Reservation> reservations) {
//        List<LocalTime> unavailableTimes = new ArrayList<>();
//
//        for (Schedule schedule : schedules) {
//            LocalTime start = schedule.getStartHour();
//            LocalTime end = schedule.getEndHour();
//            while (start.isBefore(end)) {
//                unavailableTimes.add(start);
//                start = start.plusHours(1);
//            }
//        }
//
//        unavailableTimes.addAll(
//                reservations.stream()
//                        .map(reservation -> LocalTime.parse(reservation.getStartTime()))
//                        .collect(Collectors.toList())
//        );
//
//        return unavailableTimes;
//    }
//
//    public static List<LocalTime> getAllPossibleTimes() {
//        return IntStream.range(10, 21) // From 10:00 to 20:00
//                .mapToObj(hour -> LocalTime.of(hour, 0))
//                .collect(Collectors.toList());
//    }
}