package com.c_comachi.inused.domain.report.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="report_category")
public class ReportCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_category_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Builder
    public ReportCategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
