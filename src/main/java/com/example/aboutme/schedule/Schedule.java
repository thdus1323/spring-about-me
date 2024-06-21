package com.example.aboutme.schedule;

import com.example.aboutme.schedule.enums.RestType;
import com.example.aboutme.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "schedule_tb")
@ToString(exclude = "expert")
public class Schedule {

    // 필수 입력
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 스케줄 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id", nullable = false)
    private User expert; // 상담사

    @Column(nullable = false)
    private LocalDate startDate; // 시작 날짜

    @Column(nullable = false)
    private LocalDate endDate; // 종료 날짜

    @Column(nullable = false)
    private LocalTime startHour; // 시작 시간

    @Column(nullable = false)
    private LocalTime endHour; // 종료 시간

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RestType restType; // 휴식 유형

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek; // 매주 특정 요일 (WEEKLY)

    private LocalDate specificDate; // 특정 날짜 (DATE_SPECIFIC)

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @Builder
    public Schedule(Integer id, User expert, LocalDate startDate, LocalDate endDate, LocalTime startHour, LocalTime endHour, RestType restType, DayOfWeek dayOfWeek, LocalDate specificDate, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.expert = expert;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHour = startHour;
        this.endHour = endHour;
        this.restType = restType;
        this.dayOfWeek = dayOfWeek;
        this.specificDate = specificDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
