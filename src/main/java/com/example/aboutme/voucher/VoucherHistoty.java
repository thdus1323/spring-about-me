package com.example.aboutme.voucher;

import com.example.aboutme.payment.Payment;
import com.example.aboutme.reservation.Reservation;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.enums.VoucherType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "voucher_history_tb")
@ToString(exclude = {"expert", "client", "reservation", "payments"})
public class VoucherHistoty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType voucherType;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private String imagePath;

    @Builder
    public VoucherHistoty(Integer id, User expert, User client, Reservation reservation, VoucherType voucherType, Integer price, Integer count, Integer duration, Double discount, LocalDateTime startDate, LocalDateTime endDate, Boolean isActive, List<Payment> payments, Timestamp createdAt, Timestamp updatedAt, String imagePath) {
        this.id = id;
        this.expert = expert;
        this.client = client;
        this.reservation = reservation;
        this.voucherType = voucherType;
        this.price = price;
        this.count = count;
        this.duration = duration;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.payments = payments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imagePath = imagePath;
    }
}
