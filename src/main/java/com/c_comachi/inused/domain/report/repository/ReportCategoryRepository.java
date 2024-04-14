package com.c_comachi.inused.domain.report.repository;

import com.c_comachi.inused.domain.report.entity.ReportCategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportCategoryRepository extends JpaRepository<ReportCategoryEntity, Long> {
    Optional<ReportCategoryEntity> findById(Long categoryId);
}
