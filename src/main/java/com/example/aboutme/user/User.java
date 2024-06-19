package com.example.aboutme.user;

import com.example.aboutme.alarm.Alarm;
import com.example.aboutme.comm.Comm;
import com.example.aboutme.payment.Payment;
import com.example.aboutme.refund.Refund;
import com.example.aboutme.reply.Reply;
import com.example.aboutme.user.enums.ExpertLevel;
import com.example.aboutme.user.enums.Gender;
import com.example.aboutme.user.enums.UserRole;
import com.example.aboutme.user.pr.PR;
import com.example.aboutme.user.spec.Spec;
import com.example.aboutme.voucher.Voucher;
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
    @Column(nullable = true)
    private String profileImage;

    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PR pr;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Spec> specs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Refund> refunds;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alarm> alarms;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comm> comms;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies;

    @OneToMany(mappedBy = "issuedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voucher> issuedVouchers;

    @OneToMany(mappedBy = "ownedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Voucher> ownedVouchers;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public User(Integer id, UserRole userRole, String email, String password, String name, String phone, String profileImage, String birth, Gender gender, PR pr, ExpertLevel level, List<Spec> specs, List<Payment> payments, List<Refund> refunds, List<Alarm> alarms, List<Comm> comms, List<Reply> replies, List<Voucher> issuedVouchers, List<Voucher> ownedVouchers, Timestamp createdAt, Timestamp updatedAt) {
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
        this.payments = payments;
        this.refunds = refunds;
        this.alarms = alarms;
        this.comms = comms;
        this.replies = replies;
        this.issuedVouchers = issuedVouchers;
        this.ownedVouchers = ownedVouchers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
