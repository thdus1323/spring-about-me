package com.example.aboutme.reviewSummary;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "review_summary_tb")
public class ReviewSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart; //시작기간

    @Column(name = "period_end", nullable = false)
    private LocalDate periodEnd; //끝나는 기간

    @Column(nullable = false)
    private String summary;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}