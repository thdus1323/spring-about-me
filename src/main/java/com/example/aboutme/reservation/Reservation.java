package com.example.aboutme.reservation;

import com.example.aboutme.reservation.enums.ReservationStatus;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reservation_tb")
@ToString(exclude = {"voucher"})
public class Reservation {

    // 예약 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 전문가 ID (외래 키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert;

    // 클라이언트 ID (외래 키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    // 바우처 ID (외래 키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    // 스케줄 ID (외래 키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    // 예약 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    // 예약 시작 시간
    @Column(nullable = false)
    private String startTime;

    // 예약 날짜
    @Column(nullable = false)
    private String reservationDate;

    // 예약 생성 시간
    @CreationTimestamp
    private Timestamp createdAt;

    // 예약 마지막 업데이트 시간
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Reservation(Integer id, User expert, User client, Voucher voucher, ReservationStatus status, String startTime, String reservationDate, Schedule schedule, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.expert = expert;
        this.client = client;
        this.voucher = voucher;
        this.status = status;
        this.startTime = startTime;
        this.reservationDate = reservationDate;
        this.schedule = schedule;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
