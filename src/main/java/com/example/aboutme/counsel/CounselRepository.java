package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.CounselStatus;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.CounselScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CounselRepository extends JpaRepository<Counsel, Integer> {

    // 특정 클라이언트가 특정 바우처로 특정 예약 ID 이전에 예약한 예약 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.id <= :reservationId AND (c.reservationStatus = 'SCHEDULED' OR c.reservationStatus = 'COMPLETED')")
    Integer countReservationsBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("reservationId") Integer reservationId);

    // 특정 클라이언트가 특정 바우처로 특정 결제 ID 이후에 예약한 예약 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.payment.id >= :paymentId")
    Integer countByClientIdAndVoucherIdAndReservationId(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("paymentId") Integer paymentId);

    // 특정 바우처 ID와 예약 상태를 기반으로 상담을 찾음
    Optional<Counsel> findByVoucherIdAndReservationStatus(Integer voucherId, ReservationStatus reservationStatus);

    // 특정 전문가의 특정 예약 날짜에 해당하는 예약을 찾음
    @Query("SELECT c FROM Counsel c WHERE c.expert.id = :expertId AND c.counselDate = :reservationDate")
    List<Counsel> findByExpertIdAndReservationDate(@Param("expertId") int expertId, @Param("reservationDate") String reservationDate);

    // 특정 전문가의 예약 현황을 찾음
    List<Counsel> findByExpertId(Integer expertId);

    // 특정 클라이언트의 예약 현황을 찾음
    List<Counsel> findByClientId(Integer clientId);

    // 특정 클라이언트가 특정 바우처로 예약한 첫 번째 예약 ID를 찾음
    @Query("SELECT c.id FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId ORDER BY c.id ASC")
    List<Integer> findByClientIdAndVoucherIdAndLimitOne(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId);

    // 특정 클라이언트의 특정 바우처로 특정 날짜 이전의 총 상담 수를 카운트
    @Query(value = "SELECT (SELECT COUNT(*) FROM counsel_tb WHERE client_id = :clientId AND counsel_status = 'COMPLETED') + " +
            "(SELECT COUNT(*) FROM counsel_tb WHERE client_id = :clientId AND voucher_id = :voucherId AND counsel_date <= :endDate) AS total_count", nativeQuery = true)
    Integer getTotalCount(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("endDate") String endDate);

    // 특정 클라이언트가 특정 바우처로 특정 상담 날짜 이전의 상담 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselDate <= :counselDate")
    Integer countByClientIdAndVoucherIdAndBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("counselDate") String counselDate);

    // 특정 클라이언트의 특정 바우처로 완료된 상담 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselStatus = 'COMPLETED'")
    Integer countCompletedCounselsByClientIdAndVoucherId(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId);

    // 특정 클라이언트의 특정 상담 상태를 기반으로 상담 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c JOIN c.payment p WHERE c.client.id = :clientId AND p.id = :paymentId AND c.counselStatus = 'COMPLETED'")
    Integer findByClientIdAndStateCount(@Param("clientId") Integer clientId, @Param("paymentId") Integer paymentId);


    // 특정 클라이언트의 특정 상담 상태를 기반으로 상담을 찾음
    @Query("SELECT c FROM Counsel c WHERE c.client.id = :clientId AND c.counselStatus = :state")
    List<Counsel> findByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") CounselStatus state);

//    // 특정 날짜와 시간을 기반으로 상담을 찾음
//    @Query("SELECT c FROM Counsel c WHERE c.counselDate = :counselDateAndTime")
//    List<Counsel> findCounselsByDateAndTime(@Param("counselDateAndTime") String counselDateAndTime);
//
//    @Query("SELECT COUNT(r) FROM Counsel r WHERE r.client.id = :clientId AND r.voucher.id = :voucherId AND r.id <= :reservationId AND (r.reservationStatus = 'SCHEDULED' OR r.reservationStatus = 'COMPLETED')")
//    Integer countReservationsBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("reservationId") Integer reservationId);
//
//    //클라이언트가 가지고 있는 이용권의 예약 내역을 카운트 한다.
//    @Query("SELECT count(*) FROM Counsel c WHERE c.client.id = :clientId and c.voucher = :voucherId AND c.payment.id >= :paymentId")
//    Integer countByClientIdAndVoucherIdAndReservationId(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("paymentId") Integer paymentId);
//
//    //예약대기를 SCHEDULED(확정예정)로 업데이트 하기
//    Optional<Counsel> findByVoucherIdAndReservationStatus(Integer voucherId, ReservationStatus reservationStatus);
//
//    //전문가의 스케줄에서 요일 데이터 가져오기
//    @Query("SELECT r FROM Counsel r WHERE r.expert.id = :expertId AND r.counselDate = :reservationDate")
//    List<Counsel> findByExpertIdAndReservationDate(@Param("expertId") int expertId, @Param("reservationDate") String reservationDate);
//
//    //전문가의 예약현황 확인
//    List<Counsel> findByExpertId(Integer expertId);
//
//    //클라이언트의 예약현황 확인
//    List<Counsel> findByClientId(Integer clientId);
//
//    @Query("SELECT r.id FROM Counsel r WHERE r.client.id = :clientId AND r.voucher.id = :voucherId ORDER BY r.id ASC limit 1")
//    Integer findByClientIdAndVoucherIdAndLimitOen(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId);
//
//    //상담과 예약 횟수를 계산해서 카운트하기
//    @Query(value = "SELECT (SELECT count(*) FROM COUNSEL_TB WHERE client_id = :clientId AND state = 'COMPLETED') + " +
//            "(SELECT count(*) FROM RESERVATION_TB WHERE client_id = :clientId AND voucher_id = :voucherId AND RESERVATION_DATE <= :endDate) AS total_count",
//            nativeQuery = true)
//    Integer getTotalCount(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("endDate") String endDate);
//
//    //상담의
//    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselDate <= :counselDate")
//    Integer countByClientIdAndVoucherIdAndBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("counselDate") String counselDate);
//
//    //상담완료 횟수 가져오는 쿼리
//    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselStatus = 'COMPLETED'")
//    Integer countCompletedCounselsByClientIdAndVoucherId(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId);
//
//    //상담결과에 따른 데이터 가져오기
//    @Query("SELECT count(c) FROM Counsel c WHERE c.client.id = :clientId AND c.counselStatus = :state")
//    Integer findByClientIdAndStateCount(@Param("clientId") Integer clientId, @Param("state") CounselStatus state);
//
//
//    @Query("SELECT c FROM Counsel c WHERE c.client.id = :clientId AND c.counselStatus = :state")
//    List<Counsel> findByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") CounselStatus state);
//

    @Query("SELECT c FROM Counsel c WHERE c.counselDate = :counselDateAndTime")
    List<Counsel> findCounselsByDateAndTime(@Param("counselDateAndTime") LocalDateTime counselDateAndTime);


    List<Counsel> findAllCounselByExpertId(Integer expertId);


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId ")
    Integer countAllByClientId(@Param("clientId") Integer clientId);


    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.counselStatus = :state")
    Integer countByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") CounselStatus state);


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


    @Query("SELECT c FROM Counsel c WHERE c.reservation.id = :reservationId")
    Counsel findByReservationId(@Param("reservationId") Integer reservationId);
}
