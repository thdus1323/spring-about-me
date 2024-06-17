package com.example.aboutme.comm;

import com.example.aboutme.reply.Reply;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comm_tb")
public class Comm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "comm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;

    @Builder
    public Comm(Integer id, String title, String content, User user, List<Reply> replies) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.replies = replies;
    }
}
