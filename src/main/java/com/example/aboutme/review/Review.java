package com.example.aboutme.review;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "review_tb")
@ToString(exclude = {"client", "counsel","expert"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_id", nullable = false)
    private Counsel counsel; // 외래 키로 상담 정보를 참조

    @JoinColumn(nullable = false)
    private Double score;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Review(Integer id, User client, User expert, Counsel counsel, Double score, String content, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.client = client;
        this.expert = expert;
        this.counsel = counsel;
        this.score = score;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
