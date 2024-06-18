package com.example.aboutme.payment;

import com.example.aboutme.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "payment_tb")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @CreationTimestamp
    private Timestamp paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Builder
    public Payment(Long id, double amount, String paymentMethod, User user, Reservation reservation, Timestamp paymentDate, PaymentStatus status) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.reservation = reservation;
        this.paymentDate = paymentDate;
        this.status = status;
    }
}