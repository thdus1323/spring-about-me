package com.example.aboutme.counsel;

import com.example.aboutme.counsel.CounselRequestRecord.CounselReqDTO;
import com.example.aboutme.counsel.enums.CounselStateEnum;
import com.example.aboutme.reservation.Reservation;
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
@ToString(exclude = {"client", "expert", "voucher","reservation"}) // 유효한 필드만 제외
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
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(nullable = false)
    private LocalDateTime counselDate;

    private String result; // 상담결과

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    private CounselStateEnum state;

    @Builder
    public Counsel(Integer id, User client, User expert, Voucher voucher, LocalDateTime counselDate, String result, Timestamp createdAt, Timestamp updatedAt, CounselStateEnum state, Reservation reservation) {
        this.id = id;
        this.client = client;
        this.expert = expert;
        this.voucher = voucher;
        this.counselDate = counselDate;
        this.result = result;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.state = state;
        this.reservation =reservation;
    }

    // 상담결과 업데이트
    public void updateCounsel() {
        this.result = "상담완료";
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }


}
