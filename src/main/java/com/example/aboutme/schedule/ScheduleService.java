package com.example.aboutme.schedule;

import com.example.aboutme._core.error.exception.Exception400;
import com.example.aboutme._core.utils.DayOfWeekConverter;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.counsel.CounselRepository;
import com.example.aboutme.schedule.ScheduleReqRecord.ScheduleSaveReqDTO;
import com.example.aboutme.user.SessionUser;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;

    //상담가능 시간 조회해서 있으면 업데이트 없으면 저장
    @Transactional
    public void saveSchedule(ScheduleSaveReqDTO schedule, SessionUser sessionUser) {
        User expert = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception400("전문가를 찾을 수 없습니다."));

        Map<String, ScheduleSaveReqDTO.ScheduleDTO> scheduleMap = new HashMap<>();
        scheduleMap.put("월", schedule.monday());
        scheduleMap.put("화", schedule.tuesday());
        scheduleMap.put("수", schedule.wednesday());
        scheduleMap.put("목", schedule.thursday());
        scheduleMap.put("금", schedule.friday());

        for (Map.Entry<String, ScheduleSaveReqDTO.ScheduleDTO> entry : scheduleMap.entrySet()) {
            DayOfWeek dayOfWeek = DayOfWeekConverter.toEnum(entry.getKey());
            ScheduleSaveReqDTO.ScheduleDTO scheduleDTO = entry.getValue();
            if (scheduleDTO.start() != null && !scheduleDTO.start().isEmpty() &&
                    scheduleDTO.end() != null && !scheduleDTO.end().isEmpty()) {

                Optional<Schedule> existingSchedule = scheduleRepository.findByExpertAndDayOfWeek(expert, dayOfWeek);
                Schedule newSchedule;

                if (existingSchedule.isPresent()) {
                    newSchedule = existingSchedule.get();
                } else {
                    newSchedule = new Schedule();
                    newSchedule.setExpert(expert);
                    newSchedule.setDayOfWeek(dayOfWeek);
                }

                newSchedule.setStartTime(scheduleDTO.start());
                newSchedule.setEndTime(scheduleDTO.end());
                scheduleRepository.save(newSchedule);
            }
        }
    }


    // 전문가의 특정 날짜에 대한 예약 가능 시간을 찾는 메서드
    public List<String> getAvailableTimesForDate(int expertId, LocalDate date) {
        // 해당 날짜의 요일을 구함
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 전문가의 요일별 스케줄을 조회
        List<Schedule> schedules = scheduleRepository.findByExpertIdAndDay(expertId, dayOfWeek);

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
        List<Counsel> reservations = counselRepository.findByExpertIdAndReservationDate(expertId, reservationDate);

        // 이미 예약된 시간대를 제거
        for (Counsel reservation : reservations) {
            availableTimes.remove(LocalTime.parse(reservation.getCounselDate()));
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