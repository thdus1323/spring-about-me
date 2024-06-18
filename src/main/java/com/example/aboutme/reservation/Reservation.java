package com.example.aboutme.reservation;

import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
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
@Table(name = "reservation_tb")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id", nullable = false)
    private Voucher voucher;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Reservation(Integer id, User user, Voucher voucher, String startTime, LocalDateTime reservationDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.voucher = voucher;
        this.startTime = startTime;
        this.reservationDate = reservationDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
