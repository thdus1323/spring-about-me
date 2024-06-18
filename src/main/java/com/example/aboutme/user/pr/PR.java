package com.example.aboutme.user.pr;

import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pr_tb")
public class PR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String introduction; // 소개

    @Column(columnDefinition = "TEXT", nullable = false)
    private String effects; // 효과

    @Column(columnDefinition = "TEXT", nullable = false)
    private String methods; // 방식

    @OneToOne(mappedBy = "pr", fetch = FetchType.LAZY)
    private User user;

    @Builder
    public PR(Integer id, String introduction, String effects, String methods, User user) {
        this.id = id;
        this.introduction = introduction;
        this.effects = effects;
        this.methods = methods;
        this.user = user;
    }
}
