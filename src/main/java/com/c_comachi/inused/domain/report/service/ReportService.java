package com.c_comachi.inused.domain.report.service;

import com.c_comachi.inused.domain.report.dto.request.UserReportRequestDto;
import com.c_comachi.inused.domain.report.entity.ReportCategoryEntity;
import com.c_comachi.inused.domain.report.entity.UserReportEntity;
import com.c_comachi.inused.domain.report.repository.ReportCategoryRepository;
import com.c_comachi.inused.domain.report.repository.UserReportRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final UserRepository userRepository;
    private final UserReportRepository userReportRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    @Transactional
    public void reportUser(UserReportRequestDto userReportRequestDto, String email){
        UserReportEntity reportEntity = userReportRequestDto.toReport(userRepository, reportCategoryRepository, email);
        userReportRepository.save(reportEntity);
    }
}
