package com.example.aboutme.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    //전문가의 스케줄확인
    List<Schedule> findByExpertId(Integer expertId);

}
