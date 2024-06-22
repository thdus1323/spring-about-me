package com.example.aboutme.counsel;

import com.example.aboutme.counsel.enums.StateEnum;
import com.example.aboutme.review.Review;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "counsel_tb")
@ToString(exclude = {"client", "expert", "voucher"}) // 유효한 필드만 제외
public class Counsel {
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
    private LocalDateTime counselDate;

    private String result; // 상담결과

    @CreationTimestamp

    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @Builder
    public Counsel(Integer id, User client, User expert, Voucher voucher, LocalDateTime counselDate, String result, Timestamp createdAt, Timestamp updatedAt,StateEnum state) {
        this.id = id;
        this.client = client;
        this.expert = expert;
        this.voucher = voucher;
        this.counselDate = counselDate;
        this.result = result;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.state = state;
    }
}
