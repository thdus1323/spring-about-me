package com.example.aboutme.voucher;

import com.example.aboutme.reservation.Reservation;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.enums.VoucherType;
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
@Table(name = "voucher_tb")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 바우처 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_by", nullable = false)
    private User issuedBy; // 바우처를 발급한 전문가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owned_by", nullable = false)
    private User ownedBy; // 바우처를 구입한 내담자

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation; // 예약

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType voucherType; // TEXT_THERAPY, VOICE_THERAPY, VIDEO_THERAPY

    @Column(nullable = false)
    private Integer price; // 가격

    @Column(nullable = false)
    private Integer count; // 횟수

    @Column(nullable = false)
    private Integer duration; // 시간 (분 단위로 저장)

    @Column(nullable = false)
    private Double discount; // 할인율

    @Column(nullable = false)
    private LocalDateTime startDate; // 시작 날짜

    private LocalDateTime endDate; // 종료 날짜

    @Column(nullable = false)
    private Boolean isActive; // 바우처 활성 상태

    @CreationTimestamp
    private Timestamp createdAt; // 생성일

    @UpdateTimestamp
    private Timestamp updatedAt; // 수정일

    @Builder
    public Voucher(Integer id, User issuedBy, User ownedBy, Reservation reservation, VoucherType voucherType, Integer price, Integer count, Integer duration, Double discount, LocalDateTime startDate, LocalDateTime endDate, Boolean isActive, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.issuedBy = issuedBy;
        this.ownedBy = ownedBy;
        this.reservation = reservation;
        this.voucherType = voucherType;
        this.price = price;
        this.count = count;
        this.duration = duration;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
