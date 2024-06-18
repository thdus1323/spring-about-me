package com.example.aboutme.review;

import com.example.aboutme.counsel.Counsel;
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
@Table(name = "review_tb")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "counsel_id", nullable = false)
    private Counsel counsel;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private LocalDateTime counselDate;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Review(Integer id, User user, Counsel counsel, String startTime, LocalDateTime counselDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.counsel = counsel;
        this.startTime = startTime;
        this.counselDate = counselDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
