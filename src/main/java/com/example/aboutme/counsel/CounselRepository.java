package com.example.aboutme.counsel;

import com.example.aboutme.user.UserResponseDTO.ExpertMainDTO.CounselScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CounselRepository extends JpaRepository<Counsel, Integer> {
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
