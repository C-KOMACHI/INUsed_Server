package com.c_comachi.inused.domain.report.entity;

import com.c_comachi.inused.domain.users.entity.UserEntity;
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
@Table(name="user_report")
public class UserReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_report_id", nullable = false)
    private Long id;

    @Column(name = "report_content", nullable = false)
    private String reportContent;

    @CreatedDate
    @Column(updatable = false, nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "report_category_id")
    private ReportCategoryEntity reportCategory;

    @OneToOne
    @JoinColumn(name = "reporter_id")
    private UserEntity reporter;

    @OneToOne
    @JoinColumn(name = "reportee_id")
    private UserEntity reportee;

    @Builder
    public UserReportEntity(String reportContent, LocalDateTime createdAt, ReportCategoryEntity reportCategory, UserEntity reporter, UserEntity reportee) {
        this.reportContent = reportContent;
        this.createdAt = createdAt;
        this.reportCategory = reportCategory;
        this.reporter = reporter;
        this.reportee = reportee;
    }
}
