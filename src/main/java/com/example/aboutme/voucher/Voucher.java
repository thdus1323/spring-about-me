package com.example.aboutme.voucher;

import com.example.aboutme.reservation.Reservation;
import com.example.aboutme.payment.Payment;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.enums.VoucherType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "voucher_tb")
@ToString(exclude = {"issuedBy", "ownedBy", "reservation", "payments"})
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_by", nullable = false)
    private User issuedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owned_by", nullable = false)
    private User ownedBy;

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

    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Voucher(Integer id, User issuedBy, User ownedBy, Reservation reservation, VoucherType voucherType, Integer price, Integer count, Integer duration, Double discount, LocalDateTime startDate, LocalDateTime endDate, Boolean isActive, List<Payment> payments, Timestamp createdAt, Timestamp updatedAt) {
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
        this.payments = payments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
