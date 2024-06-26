package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.CounselStateEnum;
import com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.CounselScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CounselRepository extends JpaRepository<Counsel, Integer> {


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselDate < :counselDate")
    Integer countByClientIdAndVoucherIdAndBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("counselDate") LocalDateTime counselDate);


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.state = com.example.aboutme.counsel.enums.CounselStateEnum.COMPLETED")
    Integer countCompletedCounselsByClientIdAndVoucherId(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId);

    //상담결과에 따른 데이터 가져오기
    @Query("SELECT count(c) FROM Counsel c WHERE c.client.id = :clientId AND c.state = :state")
    Integer findByClientIdAndStateCount(@Param("clientId") Integer clientId, @Param("state") CounselStateEnum state);


    @Query("SELECT c FROM Counsel c WHERE c.client.id = :clientId AND c.state = :state")
    List<Counsel> findByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") CounselStateEnum state);


    @Query("SELECT c FROM Counsel c WHERE c.counselDate = :counselDateAndTime")
    List<Counsel> findCounselsByDateAndTime(@Param("counselDateAndTime") LocalDateTime counselDateAndTime);


    List<Counsel> findAllCounselByExpertId(Integer expertId);


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId ")
    Integer countAllByClientId(@Param("clientId") Integer clientId);


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.state = :state")
    Integer countByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") CounselStateEnum state);


    @Query("""
            SELECT new com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.CounselScheduleRecord(
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
