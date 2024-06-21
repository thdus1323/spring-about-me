package com.example.aboutme.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository  extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByExpertId(Integer expertId);

    @Query("SELECT s FROM Schedule s WHERE s.expert.id = :expertId AND " +
            "((s.restType = 'WEEKLY' AND s.dayOfWeek = :dayOfWeek) OR " +
            "(s.restType = 'DATE_SPECIFIC' AND s.specificDate = :date))")
    List<Schedule> findByExpertIdAndDate(@Param("expertId") Integer expertId,
                                         @Param("date") LocalDate date,
                                         @Param("dayOfWeek") String dayOfWeek);
}
