package com.c_comachi.inused.domain.notice.controller;

import com.c_comachi.inused.domain.notice.dto.response.SearchNoticeResponseDto;
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

@Tag(name = "Notice", description = "Notice 관련 api 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {

    NoticeService noticeService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = SearchNoticeResponseDto.class))),
    })
    @Operation(summary = "공지 조회(단건 조회)")
    @GetMapping("/{notice}")
    public ResponseEntity<? super SearchNoticeResponseDto> searchNotice(@PathVariable Long noticeId) {
        ResponseEntity<? super SearchNoticeResponseDto> response = noticeService.getNotice(noticeId);
        return response;
    }

}
