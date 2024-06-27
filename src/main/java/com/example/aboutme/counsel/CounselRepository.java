package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.CounselStateEnum;
import com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.CounselScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CounselRepository extends JpaRepository<Counsel, Integer> {

    //상담과 예약 횟수를 계산해서 카운트하기
    @Query(value = "SELECT (SELECT count(*) FROM COUNSEL_TB WHERE client_id = :clientId AND state = 'COMPLETED') + " +
            "(SELECT count(*) FROM RESERVATION_TB WHERE client_id = :clientId AND voucher_id = :voucherId AND RESERVATION_DATE <= :endDate) AS total_count",
            nativeQuery = true)
    Integer getTotalCount(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("endDate") String endDate);

    //상담의
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselDate <= :counselDate")
    Integer countByClientIdAndVoucherIdAndBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("counselDate") String counselDate);

    //상담완료 횟수 가져오는 쿼리
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.state = 'COMPLETED'")
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
