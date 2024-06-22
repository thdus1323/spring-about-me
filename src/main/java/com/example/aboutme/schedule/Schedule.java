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
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "schedule_tb")
@ToString(exclude = "expert")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(name = "expert_id", nullable = false)
    private Integer expertId;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "rest_type", nullable = false)
    private RestType restType;

    @Enumerated(EnumType.STRING)
    @Column(name = "start_day")
    private DayOfWeek startDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "end_day")
    private DayOfWeek endDay;

    @Column(name = "specific_date")
    private LocalDate specificDate;

    @Column(name = "lunch_start_time")
    private LocalTime lunchStartTime;

    @Column(name = "lunch_end_time")
    private LocalTime lunchEndTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "notes")
    private String notes;

    @Builder
    public Schedule(Integer scheduleId, Integer expertId, LocalTime startTime, LocalTime endTime, RestType restType, DayOfWeek startDay, DayOfWeek endDay, LocalDate specificDate, LocalTime lunchStartTime, LocalTime lunchEndTime, LocalDateTime createdAt, LocalDateTime updatedAt, String notes) {
        this.scheduleId = scheduleId;
        this.expertId = expertId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.restType = restType;
        this.startDay = startDay;
        this.endDay = endDay;
        this.specificDate = specificDate;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.notes = notes;
    }
}
