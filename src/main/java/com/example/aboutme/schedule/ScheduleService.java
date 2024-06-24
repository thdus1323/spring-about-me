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

    // 전문가의 특정 날짜에 대한 예약 가능 시간을 찾는 메서드
    public List<String> getAvailableTimesForDate(int expertId, LocalDate date) {
        // 해당 날짜의 요일을 구함
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 전문가의 요일별 스케줄을 조회
        List<Schedule> schedules = scheduleRepository.findByExpertIdAndDayOfWeek(expertId, dayOfWeek);

        // 스케줄이 없는 경우 빈 리스트 반환
        if (schedules.isEmpty()) {
            return new ArrayList<>();
        }

        // 모든 스케줄의 예약 가능 시간을 담을 Set 생성 (중복 제거를 위해 Set 사용)
        Set<LocalTime> availableTimes = new HashSet<>();
        for (Schedule schedule : schedules) {
            // 스케줄별 예약 가능 시간을 추가
            availableTimes.addAll(getTimesBetween(schedule.getStartTime(), schedule.getEndTime()));
        }

        // 해당 날짜에 이미 예약된 시간을 조회
        String reservationDate = date.toString();
        List<Reservation> reservations = reservationRepository.findByExpertIdAndReservationDate(expertId, reservationDate);

        // 이미 예약된 시간대를 제거
        for (Reservation reservation : reservations) {
            availableTimes.remove(LocalTime.parse(reservation.getStartTime()));
        }

        // 예약 가능 시간을 정렬하여 반환
        return availableTimes.stream().sorted().map(LocalTime::toString).collect(Collectors.toList());
    }

    // 시작 시간과 종료 시간 사이의 예약 가능 시간을 1시간 단위로 반환하는 메서드
    private List<LocalTime> getTimesBetween(String start, String end) {
        List<LocalTime> times = new ArrayList<>();

        // 시작 시간과 종료 시간을 LocalTime으로 파싱
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end).minusMinutes(60); // 종료 시간에서 1시간을 뺀 시간

        // 시작 시간이 종료 시간보다 이전일 때까지 1시간 단위로 시간을 추가
        while (!startTime.isAfter(endTime)) {
            times.add(startTime);
            startTime = startTime.plusMinutes(60);
        }

        return times;
    }

}