package com.example.aboutme.schedule;

import com.example.aboutme.reservation.Reservation;
import com.example.aboutme.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

        Schedule schedule = schedules.get(0); // 여러 시간대가 있을 경우 적절한 로직으로 수정
        List<LocalTime> availableTimes = getTimesBetween(schedule.getStartTime(), schedule.getEndTime());

        String reservationDate = date.toString();
        List<Reservation> reservations = reservationRepository.findByExpertIdAndReservationDate(expertId, reservationDate);

        for (Reservation reservation : reservations) {
            availableTimes.remove(LocalTime.parse(reservation.getStartTime()));
        }

        return availableTimes.stream().map(LocalTime::toString).collect(Collectors.toList());
    }

    private List<LocalTime> getTimesBetween(String start, String end) {
        List<LocalTime> times = new ArrayList<>();
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end).minusMinutes(60); // 예약 가능 시간을 종료 시간보다 1시간 이전으로 설정
        for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusMinutes(60)) {
            times.add(time);
        }
        return times;
    }
}