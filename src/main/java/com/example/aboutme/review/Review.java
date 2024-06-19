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

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Counsel counsel;

    @Column(nullable = false)
    private LocalDateTime counselDate;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private String score; // 추가

    @Builder
    public Review(Integer id, User user, Counsel counsel, LocalDateTime counselDate, Timestamp createdAt, Timestamp updatedAt, String score) {
        this.id = id;
        this.user = user;
        this.counsel = counsel;
        this.counselDate = counselDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.score = score;
    }
}
