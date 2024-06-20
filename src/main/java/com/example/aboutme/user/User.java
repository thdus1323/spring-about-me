package com.example.aboutme.user;

import com.example.aboutme.user.enums.ExpertLevel;
import com.example.aboutme.user.enums.Gender;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.pr.PR;
import com.example.aboutme.user.spec.Spec;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_tb")
@ToString(exclude = {"pr", "specs", "payments", "refunds", "alarms", "comms", "replies", "vouchers"})
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

    private String expertTitle; // 상담사 타이틀

    @Enumerated(EnumType.STRING)
    private ExpertLevel level; // 상담사 레벨

    @Column(nullable = true)
    private String profileImage;

    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PR pr;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spec> specs;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public User(Integer id, UserRole userRole, String email, String password, String name, String phone, String profileImage, String birth, Gender gender, PR pr, List<Spec> specs, Timestamp createdAt, Timestamp updatedAt, String expertTitle) {
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
        this.specs = specs;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expertTitle = expertTitle;
    }
}
