package com.example.aboutme.comm;

import com.example.aboutme.comm.enums.CommCategory;
import com.example.aboutme.reply.Reply;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comm_tb")
@ToString(exclude = {"user", "replies"})
public class Comm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommCategory category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "comm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Comm(Integer id, String title, String content, User user, List<Reply> replies, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.replies = replies;
        this.createdAt = createdAt;
    }
}