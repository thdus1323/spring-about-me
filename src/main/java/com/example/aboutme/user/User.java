package com.example.aboutme.user;

import com.example.aboutme.alarm.Alarm;
import com.example.aboutme.book.Book;
import com.example.aboutme.comm.Comm;
import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.reply.Reply;
import com.example.aboutme.voucher.Voucher;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Role role; // CLIENT, EXPERT

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer birthYear;

    @Column(nullable = false)
    private String gender;

    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comm> communities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reply> replies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alarm> alarms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Voucher> vouchers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Counsel> counsels;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public User(Integer id, Role role, String username, String email, String password, Integer birthYear, String gender, String profileImage, List<Comm> communities, List<Reply> replies, List<Alarm> alarms, List<Voucher> vouchers, List<Book> books, List<Counsel> counsels, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthYear = birthYear;
        this.gender = gender;
        this.profileImage = profileImage;
        this.communities = communities;
        this.replies = replies;
        this.alarms = alarms;
        this.vouchers = vouchers;
        this.books = books;
        this.counsels = counsels;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
