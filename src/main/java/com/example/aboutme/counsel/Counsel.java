package com.example.aboutme.counsel;

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
@Table(name = "counseling_tb")
public class Counsel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usage_right_id", nullable = false)
    private Voucher voucher;

    @Column(nullable = false)
    private LocalDateTime counselingDate;

    @Column(nullable = false)
    private String result;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Counsel(Integer id, User user, Voucher voucher, LocalDateTime counselingDate, String result, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.voucher = voucher;
        this.counselingDate = counselingDate;
        this.result = result;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}