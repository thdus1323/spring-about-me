package com.example.aboutme.user.intro;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "intro_tb")
public class Intro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 스펙 아이디

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String effect;

    @Builder
    public Intro(Integer id, String intro, String method, String effect) {
        this.id = id;
        this.intro = intro;
        this.method = method;
        this.effect = effect;
    }
}
