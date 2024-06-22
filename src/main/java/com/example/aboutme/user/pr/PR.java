package com.example.aboutme.user.pr;

import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pr_tb")
@ToString(exclude = {"user"})
public class PR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // pr 아이디


    @Column(nullable = false)
    private String intro; // 소개

    @Column(nullable = false)
    private String effects; // 효과

    @Column(nullable = false)
    private String methods; // 방식

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private User user;

    @Builder
    public PR(Integer id, String intro, String effects, String methods, User user) {
        this.id = id;
        this.intro = intro;
        this.effects = effects;
        this.methods = methods;
        this.user = user;
    }
}
