package com.example.aboutme.reservation;

import com.example.aboutme.reservation.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {




    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.client.id = :clientId AND r.voucher.id = :voucherId AND r.reservationDate < :reservationDate AND (r.status = com.example.aboutme.reservation.enums.ReservationStatus.SCHEDULED OR r.status = com.example.aboutme.reservation.enums.ReservationStatus.COMPLETED)")
    Integer countReservationsBeforeDate(@Param("clientId") Integer clientId, @Param("voucherId") Integer voucherId, @Param("reservationDate") String reservationDate);



//이용권 조회
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.voucher.id = :voucherId AND r.status = :status")
    Integer countByVoucherIdAndStatus(@Param("voucherId") Integer voucherId, @Param("status") ReservationStatus status);


    @Query("SELECT r FROM Reservation r WHERE r.client.id = :clientId")
    List<Reservation> findByUserId(@Param("clientId") Integer clientId);

    //예약대기를 SCHEDULED(확정예정)로 업데이트 하기
    Optional<Reservation> findByVoucherIdAndStatus(Integer voucherId, ReservationStatus status);

    //전문가의 스케줄에서 요일 데이터 가져오기
    @Query("SELECT r FROM Reservation r WHERE r.expert.id = :expertId AND r.reservationDate = :reservationDate")
    List<Reservation> findByExpertIdAndReservationDate(@Param("expertId") int expertId, @Param("reservationDate") String reservationDate);

    //전문가의 예약현황 확인
    List<Reservation> findByExpertId(Integer expertId);

    //클라이언트의 예약현황 확인
    List<Reservation> findByClientId(Integer clientId);



}
