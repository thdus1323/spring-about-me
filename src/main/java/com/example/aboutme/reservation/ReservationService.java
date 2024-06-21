package com.example.aboutme.reservation;

import com.example.aboutme.reservation.resrvationResponse.ReservationListDTO;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.schedule.ScheduleRepository;
import com.example.aboutme.schedule.ScheduleResponse.AvailableTimeRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;




    @Transactional
    public void Reservation (Reservation reservation) {
        // 인증처리

        // 조건처리

        // 리스트
        List<Reservation> reservations = reservationRepository.findAll();

    }

    //전문가 스케줄 불러오기
    public List<ReservationListDTO> getExpertSchedules(Integer expertId,Integer voucherId) {
        return scheduleRepository.findByExpertId(expertId).stream()
                .map(schedule -> new ReservationListDTO(
                        schedule.getExpert().getId(),
                        schedule.getStartDate().toString(),
                        schedule.getEndDate().toString(),
                        schedule.getStartHour().toString(),
                        schedule.getEndHour().toString(),
                        schedule.getRestType().toString(),
                        schedule.getDayOfWeek().toString(),
                        schedule.getSpecificDate() != null ? schedule.getSpecificDate().toString() : null
                ))
                .collect(Collectors.toList());
    }
}
