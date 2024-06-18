package com.example.aboutme.user.spec;

import com.example.aboutme.user.User;
import com.example.aboutme.user.enums.SpecType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "spec_tb")
public class Spec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpecType specType;

    @Column(nullable = false)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Spec(Integer id, SpecType specType, String details, User user) {
        this.id = id;
        this.specType = specType;
        this.details = details;
        this.user = user;
    }
}