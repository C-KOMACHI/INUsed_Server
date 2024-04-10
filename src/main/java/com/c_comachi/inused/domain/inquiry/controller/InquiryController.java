package com.c_comachi.inused.domain.inquiry.controller;

import com.c_comachi.inused.domain.inquiry.dto.request.CreateUserInquiryRequestDto;
import com.c_comachi.inused.domain.inquiry.dto.request.EditUserInquiryRequestDto;
import com.c_comachi.inused.domain.inquiry.dto.response.GetAllUserInquiryResponseDto;
import com.c_comachi.inused.domain.inquiry.dto.response.GetUserInquiryResponseDto;
import com.c_comachi.inused.domain.inquiry.service.UserInquiryService;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Inquiry", description = "Inquiry 관련 api 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inquiry")
public class InquiryController {

    private final UserInquiryService userInquiryService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = RegisterResponseDto.class))),
    })
    @Operation(summary = "문의 생성")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createInquiry(@RequestBody @Valid CreateUserInquiryRequestDto requestBody, @AuthenticationPrincipal UserDetails user) {
        userInquiryService.createInquiry(requestBody, user.getUsername());
        return ResponseDto.suc();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = GetUserInquiryResponseDto.class))),
    })
    @Operation(summary = "문의 조회 (단건 조회)")
    @GetMapping("/{userInquiryId}")
    public ResponseEntity<? super GetUserInquiryResponseDto> getUserInquiry(@PathVariable("userInquiryId") Long userInquiryId) {
        ResponseEntity<? super GetUserInquiryResponseDto> response = userInquiryService.getUserInquiry(userInquiryId);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = GetAllUserInquiryResponseDto.class))),
    })
    @Operation(summary = "문의 조회 (전체 조회)")
    @GetMapping("")
    public ResponseEntity<? super GetAllUserInquiryResponseDto> getUserInquiry(@AuthenticationPrincipal UserDetails user) {
        ResponseEntity<? super GetAllUserInquiryResponseDto> response = userInquiryService.getAllUserInquiry(user.getUsername());
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "문의 수정")
    @PatchMapping("/{userInquiryId}")
    public ResponseEntity<ResponseDto> editInquiry(@PathVariable("userInquiryId") Long userInquiryId,
                                                   @RequestBody @Valid EditUserInquiryRequestDto requestDto) {
        userInquiryService.editInquiry(userInquiryId, requestDto.toUserInquiryEditInfo());
        return ResponseDto.suc();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "문의 삭제")
    @DeleteMapping("/{userInquiryId}")
    public ResponseEntity<ResponseDto> deleteInquiry(@PathVariable("userInquiryId") Long userInquiryId) {
        userInquiryService.deleteInquiry(userInquiryId);
        return ResponseDto.suc();
    }

}
