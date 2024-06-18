package com.example.aboutme.reservation;

import com.example.aboutme.reservation.enums.ReservationStatus;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reservation_tb")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 예약 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert; // 전문가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client; // 내담자

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher; // 바우처

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status; // 예약 상태

    @Column(nullable = false)
    private String startTime; // 시작 시간

    @Column(nullable = false)
    private LocalDateTime reservationDate; // 예약 날짜

    @CreationTimestamp
    private Timestamp createdAt; // 생성일

    @UpdateTimestamp
    private Timestamp updatedAt; // 수정일

    @Builder
    public Reservation(Integer id, User expert, User client, Voucher voucher, ReservationStatus status, String startTime, LocalDateTime reservationDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.expert = expert;
        this.client = client;
        this.voucher = voucher;
        this.status = status;
        this.startTime = startTime;
        this.reservationDate = reservationDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

//    // 예약 취소
//    public void cancelReservation() {
//        this.status = ReservationStatus.CANCELLED;
//        if (this.voucher != null) {
//            this.voucher.setIsActive(true);
//            this.voucher.setReservation(null);
//        }
//    }
}
