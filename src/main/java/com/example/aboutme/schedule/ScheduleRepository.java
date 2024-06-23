package com.example.aboutme.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    // 전문가 ID와 요일로 스케줄 조회
    @Query("SELECT s FROM Schedule s WHERE s.expert.id = :expertId AND s.dayOfWeek = :dayOfWeek")
    List<Schedule> findByExpertIdAndDayOfWeek(@Param("expertId") Integer expertId, @Param("dayOfWeek") DayOfWeek dayOfWeek);


    @Query("SELECT s FROM Schedule s WHERE s.expert.id = :expertId AND s.dayOfWeek = :dayOfWeek AND s.startTime <= :startTime AND s.endTime >= :startTime")
    Optional<Schedule> findByExpertIdAndDayOfWeekAndStartTime(
            @Param("expertId") Integer expertId,
            @Param("dayOfWeek") DayOfWeek dayOfWeek,
            @Param("startTime") String startTime);


    //전문가의 스케줄확인
    List<Schedule> findByExpertId(Integer expertId);

}
