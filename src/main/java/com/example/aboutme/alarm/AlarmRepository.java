package com.example.aboutme.alarm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlarmRepository extends JpaRepository<Alarm, Integer> {

    @Query("""
            SELECT COUNT(a)
            FROM Alarm a
            WHERE a.receiver.id = :receiverId AND a.read = false
            """)
        Integer countUnreadAlarmsByReceiverId(@Param("receiverId") Integer receiverId);
}
