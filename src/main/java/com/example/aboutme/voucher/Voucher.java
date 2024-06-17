package com.example.aboutme.voucher;

import com.example.aboutme.user.User;
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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String usageType;

    @Column(nullable = false)
    private Integer usageCount;

    @Column(nullable = false)
    private Integer remainingCount;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Voucher(Integer id, User user, String usageType, Integer usageCount, Integer remainingCount, LocalDateTime startDate, LocalDateTime endDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.usageType = usageType;
        this.usageCount = usageCount;
        this.remainingCount = remainingCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
