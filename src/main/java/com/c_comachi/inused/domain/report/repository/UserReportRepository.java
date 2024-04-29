package com.c_comachi.inused.domain.report.repository;

import com.c_comachi.inused.domain.report.entity.UserReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReportRepository extends JpaRepository<UserReportEntity, Long> {

}
