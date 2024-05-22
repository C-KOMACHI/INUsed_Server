package com.c_comachi.inused.domain.report.dto.request;

import com.c_comachi.inused.domain.report.entity.ReportCategoryEntity;
import com.c_comachi.inused.domain.report.entity.UserReportEntity;
import com.c_comachi.inused.domain.report.repository.ReportCategoryRepository;
import com.c_comachi.inused.domain.report.repository.UserReportRepository;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class UserReportRequestDto {
    @Schema(description = "신고할 유저의 id")
    private Long reporteeUserId;

    private Long categoryId;

    private String content;

    public UserReportEntity toReport(UserRepository userRepository,
                                     ReportCategoryRepository reportCategoryRepository,
                                     String email){

        ReportCategoryEntity reportCategory = reportCategoryRepository.findById(getCategoryId()).get();
        UserEntity reportee = userRepository.findById(getReporteeUserId()).get();
        UserEntity reporter = userRepository.findByEmail(email).get();

        return UserReportEntity.builder()
                .reportContent(content)
                .reportCategory(reportCategory)
                .reporter(reporter)
                .reportee(reportee)
                .createdAt(LocalDateTime.now())
                .build();
    }

}
