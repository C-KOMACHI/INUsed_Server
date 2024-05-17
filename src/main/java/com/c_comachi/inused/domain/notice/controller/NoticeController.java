package com.c_comachi.inused.domain.notice.controller;

import com.c_comachi.inused.domain.notice.dto.response.SearchNoticeResponseDto;
import com.c_comachi.inused.domain.notice.dto.response.ViewNoticeResponseDto;
import com.c_comachi.inused.domain.notice.service.NoticeService;
import com.c_comachi.inused.domain.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Notice", description = "Notice 관련 api 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = SearchNoticeResponseDto.class))),
    })
    @Operation(summary = "공지 조회(단건 조회)")
    @GetMapping("/{noticeId}")
    public ResponseEntity<? super SearchNoticeResponseDto> searchNotice(@PathVariable("noticeId") Long noticeId) {
        return noticeService.searchNotice(noticeId);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ViewNoticeResponseDto.class))),
    })
    @Operation(summary = "공지 조회(전체 조회)")
    @GetMapping("")
    public ResponseEntity<? super ViewNoticeResponseDto> viewNotice(){
        return noticeService.getNotices();
    }

}
