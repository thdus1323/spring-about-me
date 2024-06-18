package com.example.aboutme.reply;

import com.example.aboutme.comm.Comm;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reply_tb")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 글쓴이

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comm_id", nullable = false)
    private Comm comm; // 커뮤니티

    @Column(nullable = false)
    private String summary; // 내용요약

    @Column(nullable = false)
    private String causeAnalysis; // 원인분석

    @Column(nullable = false)
    private String solution; // 대처방향제시

    @CreationTimestamp
    private Timestamp createdAt; // 작성일

    @Builder
    public Reply(Integer id, User user, Comm comm, String summary, String causeAnalysis, String solution, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.comm = comm;
        this.summary = summary;
        this.causeAnalysis = causeAnalysis;
        this.solution = solution;
        this.createdAt = createdAt;
    }
}
