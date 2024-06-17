package com.example.aboutme.alarm;

import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "alarm_tb")
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Alarm(Integer id, String message, User user, Timestamp createdAt) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.createdAt = createdAt;
    }
}
