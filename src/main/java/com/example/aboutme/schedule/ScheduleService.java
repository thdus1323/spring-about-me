package com.example.aboutme.schedule;

import com.example.aboutme.reservation.Reservation;
import com.example.aboutme.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;


    public List<String> getAvailableTimesForDate(int expertId, LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Schedule> schedules = scheduleRepository.findByExpertIdAndDayOfWeek(expertId, dayOfWeek);

        if (schedules.isEmpty()) {
            return new ArrayList<>();
        }

        // 모든 스케줄에 대한 예약 가능 시간 계산
        Set<LocalTime> availableTimes = new HashSet<>();
        for (Schedule schedule : schedules) {
            availableTimes.addAll(getTimesBetween(schedule.getStartTime(), schedule.getEndTime()));
        }

        String reservationDate = date.toString();
        List<Reservation> reservations = reservationRepository.findByExpertIdAndReservationDate(expertId, reservationDate);

        // 이미 예약된 시간 제거
        for (Reservation reservation : reservations) {
            availableTimes.remove(LocalTime.parse(reservation.getStartTime()));
        }

        // 정렬된 리스트로 반환
        return availableTimes.stream().sorted().map(LocalTime::toString).collect(Collectors.toList());
    }

    private List<LocalTime> getTimesBetween(String start, String end) {
        List<LocalTime> times = new ArrayList<>();
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end).minusMinutes(60); // 종료 시간보다 1시간 이전

        while (!startTime.isAfter(endTime)) {
            times.add(startTime);
            startTime = startTime.plusMinutes(60);
        }

        return times;
    }

}