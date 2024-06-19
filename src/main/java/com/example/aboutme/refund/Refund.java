package com.example.aboutme.refund;

import com.example.aboutme.payment.Payment;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "refund_tb")
@ToString(exclude = {"user", "payment"})
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @CreationTimestamp
    private Timestamp refundDate;

    @Builder
    public Refund(Integer id, double amount, User user, Payment payment, Timestamp refundDate) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.payment = payment;
        this.refundDate = refundDate;
    }
}
