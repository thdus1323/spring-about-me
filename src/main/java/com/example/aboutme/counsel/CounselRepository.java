package com.example.aboutme.counsel;

import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.CounselScheduleRecord;
import com.example.aboutme.counsel.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CounselRepository extends JpaRepository<Counsel, Integer> {

    @Query("SELECT c FROM Counsel c WHERE c.counselDate = :counselDateAndTime")
    List<Counsel> findCounselsByDateAndTime(@Param("counselDateAndTime") LocalDateTime counselDateAndTime);

    List<Counsel> findAllCounselByExpertId(Integer expertId);


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.state = :state")
    Integer countByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") StateEnum state);

    @Query("""
            SELECT new com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.CounselScheduleRecord(
                c.id, 
                c.client.name, 
                c.counselDate, 
                v.voucherType, 
                v.duration
            ) 
            FROM Counsel c 
            JOIN c.voucher v  
            WHERE c.expert.id = :expertId
            """)
    List<CounselScheduleRecord> findCounselScheduleRecordsByExpertId(@Param("expertId") Integer expertId);
}
