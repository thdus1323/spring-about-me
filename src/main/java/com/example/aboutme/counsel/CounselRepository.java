package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.CounselStatus;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.user.User;
import com.example.aboutme.user.UserResponseRecord.ExpertMainDTO.CounselScheduleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface CounselRepository extends JpaRepository<Counsel, Integer> {

    // 특정 클라이언트가 특정 바우처로 특정 예약 ID 이전에 예약한 예약 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.id <= :reservationId AND (c.reservationStatus = 'RESERVATION_SCHEDULED' OR c.reservationStatus = 'RESERVATION_COMPLETED')")
    Integer countReservationsBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("reservationId") Integer reservationId);

    // 특정 클라이언트가 특정 바우처로 특정 결제 ID 이후에 예약한 예약 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.payment.id >= :paymentId AND (c.reservationStatus = 'RESERVATION_SCHEDULED' OR c.reservationStatus = 'RESERVATION_COMPLETED')")
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
    @Query(value = "SELECT (SELECT COUNT(*) FROM counsel_tb WHERE client_id = :clientId AND counsel_status = 'COUNSEL_COMPLETED') + " +
            "(SELECT COUNT(*) FROM counsel_tb WHERE client_id = :clientId AND voucher_id = :voucherId AND counsel_date <= :counsel_date) AS total_count", nativeQuery = true)
    Integer getTotalCount(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("counsel_date") String counsel_date);

    // 특정 클라이언트가 특정 바우처로 특정 상담 날짜 이전의 상담 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselDate <= :counselDate")
    Integer countByClientIdAndVoucherIdAndBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("counselDate") String counselDate);

    // 특정 클라이언트의 특정 바우처로 완료된 상담 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c WHERE c.client.id = :clientId AND c.voucher.id = :voucherId AND c.counselStatus = 'COUNSEL_COMPLETED'")
    Integer countCompletedCounselsByClientIdAndVoucherId(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId);

    // 특정 클라이언트의 특정 상담 상태를 기반으로 상담 수를 카운트
    @Query("SELECT COUNT(c) FROM Counsel c JOIN c.payment p WHERE c.client.id = :clientId AND p.id = :paymentId AND c.counselStatus = 'COUNSEL_COMPLETED'")
    Integer findByClientIdAndStateCount(@Param("clientId") Integer clientId, @Param("paymentId") Integer paymentId);

    // 특정 클라이언트의 특정 상담 상태를 기반으로 상담을 찾음
    @Query("SELECT c FROM Counsel c WHERE c.client.id = :clientId AND c.counselStatus = :state")
    List<Counsel> findByClientIdAndState(@Param("clientId") Integer clientId, @Param("state") CounselStatus state);


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

//
//    @Query("SELECT c FROM Counsel c WHERE c.reservation.id = :reservationId")
//    Counsel findByReservationId(@Param("reservationId") Integer reservationId);

    Page<Counsel> findAllByExpert(User expert, Pageable pageable);
    Integer countByClient(User client);
    Integer countByClientAndCounselStatus(User client, CounselStatus state);

}
