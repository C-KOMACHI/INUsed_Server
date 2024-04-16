package com.c_comachi.inused.domain.report.controller;

import com.c_comachi.inused.domain.report.dto.request.UserReportRequestDto;
import com.c_comachi.inused.domain.report.service.ReportService;
import com.c_comachi.inused.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/report")
@Tag(name = "report", description = "유저 신고 API 모음")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "유저 신고 기능", description = "헤더에 토큰을 포함하고, 요청 바디에 'reporteeUserId'(신고할 유저의 아이디)를 JSON 형식으로 보내주세요.")
    @PostMapping("/user")
    public ResponseEntity<ResponseDto> reportComment(@Valid @RequestBody UserReportRequestDto requestDto, @AuthenticationPrincipal UserDetails user) {
        reportService.reportUser(requestDto, user.getUsername());
        return ResponseDto.suc();
    }

//    @Operation(summary = "투표 신고 기능", description = "헤더에 토큰을 포함하고, 요청 바디에 'reportedVoteId'(신고할 투표의 아이디)를 JSON 형식으로 보내주세요.")
//    @PostMapping("/votes")
//    public ResponseEntity reportVote(@Valid @RequestBody ReportVoteRequest reportVoteRequest, @RequestAttribute Long userId) {
//        reportService.reportVote(reportVoteRequest.toServiceRequest(), userId, ReportType.Vote);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
