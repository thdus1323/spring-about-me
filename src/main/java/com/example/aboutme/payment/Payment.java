package com.example.aboutme.payment;

import com.example.aboutme.payment.enums.PaymentMethods;
import com.example.aboutme.payment.enums.PaymentStatus;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
import com.example.aboutme.voucher.enums.VoucherType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_tb")
@ToString(exclude = {"client", "voucher", "expert"})
public class Payment {
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

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethods paymentMethod;

    @CreationTimestamp
    private Timestamp paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType voucherType; //바우처 정상가

    @Column(nullable = false)
    private double voucherPrice; //바우처 정상가

    @Column(nullable = false)
    private Integer voucherCount; //바우처 횟수

    @Column(nullable = false)
    private Integer voucherDuration; //바우처 이용시간

    //임시저장할때는 널 이어야함.
    private String impUid; // 아임포트 거래 고유 ID

    @Column(nullable = false)
    private String merchantUid; // 상점 거래 고유 ID

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder

    public Payment(Integer id, User client, User expert, Voucher voucher, double amount, PaymentMethods paymentMethod, Timestamp paymentDate, PaymentStatus paymentStatus, VoucherType voucherType, double voucherPrice, Integer voucherCount, Integer voucherDuration, String impUid, String merchantUid, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.client = client;
        this.expert = expert;
        this.voucher = voucher;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.voucherType = voucherType;
        this.voucherPrice = voucherPrice;
        this.voucherCount = voucherCount;
        this.voucherDuration = voucherDuration;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
