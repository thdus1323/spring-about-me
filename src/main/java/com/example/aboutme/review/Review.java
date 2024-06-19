package com.example.aboutme.review;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.user.User;
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
@Table(name = "review_tb")
@ToString(exclude = {"user", "counsel"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_id", nullable = false)
    private Counsel counsel;  // 외래 키로 상담 정보를 참조

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Review(Integer id, User user, Counsel counsel, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.counsel = counsel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
