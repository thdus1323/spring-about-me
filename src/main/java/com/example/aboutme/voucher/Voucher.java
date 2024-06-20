package com.example.aboutme.voucher;

import com.example.aboutme.user.User;
import com.example.aboutme.voucher.enums.VoucherType;
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
@Table(name = "voucher_tb")
@ToString(exclude = {"expert"})
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoucherType voucherType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer duration;

    private String imagePath;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Voucher(Integer id, VoucherType voucherType, User expert, Double price, Integer count, Integer duration, String imagePath, LocalDateTime startDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.voucherType = voucherType;
        this.expert = expert;
        this.price = price;
        this.count = count;
        this.duration = duration;
        this.imagePath = imagePath;
        this.startDate = startDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
