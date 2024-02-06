package com.c_comachi.inused.domain.report.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="manager_report")
public class ManagerReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_report_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "user_report_id")
    private UserReportEntity userReport;

    @Builder
    public ManagerReportEntity(Long id, String content, LocalDateTime createdAt, UserReportEntity userReport) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userReport = userReport;
    }
}
