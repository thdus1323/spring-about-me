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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private static final Logger logger = Logger.getLogger(ScheduleService.class.getName());
    private final ScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;

    public List<String> getAvailableTimesForDate(int expertId, LocalDate date) {
        logger.log(Level.INFO, "Finding available times for expertId: {0} on date: {1}", new Object[]{expertId, date});

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        logger.log(Level.INFO, "Day of week: {0}", dayOfWeek);

        List<Schedule> schedules = scheduleRepository.findByExpertIdAndDayOfWeek(expertId, dayOfWeek);
        logger.log(Level.INFO, "Schedules found: {0}", schedules.size());

        if (schedules.isEmpty()) {
            logger.log(Level.WARNING, "No schedules found for expertId: {0} on dayOfWeek: {1}", new Object[]{expertId, dayOfWeek});
            return new ArrayList<>();
        }

        Schedule schedule = schedules.get(0);  // 여러 시간대가 있을 경우 적절한 로직으로 수정
        logger.log(Level.INFO, "Using schedule: {0}", schedule);

        List<LocalTime> availableTimes = getTimesBetween(schedule.getStartTime(), schedule.getEndTime());
        logger.log(Level.INFO, "Available times before reservations: {0}", availableTimes);

        String reservationDate = date.toString();
        List<Reservation> reservations = reservationRepository.findByExpertIdAndReservationDate(expertId, reservationDate);
        logger.log(Level.INFO, "Reservations on date {0}: {1}", new Object[]{reservationDate, reservations.size()});

        for (Reservation reservation : reservations) {
            LocalTime reservationTime = LocalTime.parse(reservation.getStartTime());
            logger.log(Level.INFO, "Removing reserved time: {0}", reservationTime);
            availableTimes.remove(reservationTime);
        }

        List<String> result = availableTimes.stream().map(LocalTime::toString).collect(Collectors.toList());
        logger.log(Level.INFO, "Available times after reservations: {0}", result);

        return result;
    }

    private List<LocalTime> getTimesBetween(String start, String end) {
        logger.log(Level.INFO, "Calculating times between {0} and {1}", new Object[]{start, end});

        List<LocalTime> times = new ArrayList<>();
        LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.parse(end);

        for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusMinutes(60)) {
            times.add(time);
            logger.log(Level.FINE, "Added time: {0}", time);
        }

        logger.log(Level.INFO, "Calculated times: {0}", times);
        return times;
    }
}