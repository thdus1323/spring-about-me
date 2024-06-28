package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.CounselStatus;
import com.example.aboutme.counsel.enums.ReservationStatus;
import com.example.aboutme.payment.Payment;
import com.example.aboutme.schedule.Schedule;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "counsel_tb")
@ToString(exclude = {"client", "expert", "voucher", "schedule", "payment"}) // 유효한 필드만 제외
public class Counsel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    // 스케줄 ID (외래 키)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    // 예약 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus reservationStatus;

    // 상담상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CounselStatus counselStatus;

    // 예약 요일
    @Column(nullable = false)
    private String dayOfWeek;  // 요일 (월요일, 화요일, 등)

    //상담날짜
    @Column(nullable = false)
    private String counselDate;

    //상담시간
    @Column(nullable = false)
    private String counselTime;

    private String result; // 상담결과

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


    @Builder
    public Counsel(Integer id, User client, User expert, Voucher voucher, Payment payment, Schedule schedule, ReservationStatus reservationStatus, CounselStatus counselStatus, String dayOfWeek, String counselDate, String counselTime, String result, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.client = client;
        this.expert = expert;
        this.voucher = voucher;
        this.payment = payment;
        this.schedule = schedule;
        this.reservationStatus = reservationStatus;
        this.counselStatus = counselStatus;
        this.dayOfWeek = dayOfWeek;
        this.counselDate = counselDate;
        this.counselTime = counselTime;
        this.result = result;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    // 상담내용 수정
    public void completeCounsel(){
        this.counselStatus = CounselStatus.COUNSEL_COMPLETED;
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
