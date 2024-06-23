package com.example.aboutme.payment;

import com.example.aboutme.payment.enums.PaymentStatus;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_tb")
@ToString(exclude = {"client", "voucher"})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @CreationTimestamp
    private Timestamp paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(nullable = true)
    private String impUid; // 아임포트 거래 고유 ID

    @Column(nullable = true)
    private String merchantUid; // 상점 거래 고유 ID

    @Builder
    public Payment(Integer id, double amount, String paymentMethod, User client, Voucher voucher, Timestamp paymentDate, PaymentStatus status, String impUid, String merchantUid) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.client = client;
        this.voucher = voucher;
        this.paymentDate = paymentDate;
        this.status = status;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
    }
}
