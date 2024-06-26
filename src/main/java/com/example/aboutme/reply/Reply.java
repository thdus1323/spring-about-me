package com.example.aboutme.reply;

import com.example.aboutme.comm.Comm;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reply_tb")
@ToString(exclude = {"user", "comm"})
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 댓글 단 사람

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comm_id", nullable = false)
    private Comm comm;

    //일반 유저 댓글
    private String content;

    //전문가 댓글
    private String introduction; // 소개글
    private String summary; // 사연 요약
    private String causeAnalysis; // 원인 분석
    private String solution; // 대처 방향

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Reply(Integer id, User user, Comm comm, String content, String introduction, String summary, String causeAnalysis, String solution, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.comm = comm;
        this.content = content;
        this.introduction = introduction;
        this.summary = summary;
        this.causeAnalysis = causeAnalysis;
        this.solution = solution;
        this.createdAt = createdAt;
    }

    // 전문가 댓글 생성자
    @Builder
    public Reply(User user, Comm comm, ReplyResponse.ReplyDataDTO newReply) {
        this.user = user;
        this.comm = comm;
        this.introduction = newReply.getIntroduction(); // 소개글
        this.solution = newReply.getSolution(); // 대처 방향
        this.summary = newReply.getSummary(); // 사연 요약
        this.causeAnalysis = newReply.getAnalysis(); // 원인 분석
    }

    // 일반인 댓글 생성자
    public Reply(User user, Comm comm, ReplyResponse.ClientReplyDataDTO newReply) {
        this.user = user;
        this.comm = comm;
        this.content = newReply.getContent();
    }
}