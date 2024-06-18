package com.example.aboutme.user;

import com.example.aboutme.user.enums.ExpertLevel;
import com.example.aboutme.user.enums.Gender;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.pr.PR;
import com.example.aboutme.user.spec.Spec;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_tb")
public class User {
    // 필수 입력
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 유저 아이디

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole; // CLIENT, EXPERT

    @Column(nullable = false, unique = true)
    private String email; // 이메일을 아이디로 씀

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String name; // client는 닉네임, expert는 이름으로 씀

    @Column(nullable = false)
    private String phone; // 전화번호

    // 아래부터는 필수가 아님
    private String profileImage; // 프로필 이미지

    private String birth; // 출생연도

    @Enumerated(EnumType.STRING)
    private Gender gender; // MAN, WOMAN, OTHER

    // 전문가만 입력
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pr_id")
    private PR pr; // 소개(망설임), 효과, 방식

    @Enumerated(EnumType.STRING)
    private ExpertLevel level; // 1급: LEVEL1, 2급: LEVEL2

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spec> specs; // 스펙 목록

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public User(Integer id, UserRole userRole, String email, String password, String name, String phone, String profileImage, String birth, Gender gender, PR pr, ExpertLevel level, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userRole = userRole;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.profileImage = profileImage;
        this.birth = birth;
        this.gender = gender;
        this.pr = pr;
        this.level = level;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}