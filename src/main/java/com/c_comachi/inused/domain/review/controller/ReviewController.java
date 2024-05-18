package com.c_comachi.inused.domain.review.controller;

import com.c_comachi.inused.domain.review.dto.request.CreateReviewRequestDto;
import com.c_comachi.inused.domain.review.dto.response.StartReviewResponseDto;
import com.c_comachi.inused.domain.review.service.ReviewService;
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

@Tag(name = "Review" , description = "Review 관련 API 모음")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "리뷰 생성")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createReview(@RequestBody @Valid CreateReviewRequestDto createReviewRequestDto,
                                                    @AuthenticationPrincipal UserDetails user){
        reviewService.createReview(createReviewRequestDto, user);
        return ResponseDto.suc();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "리뷰 시작", description = "거래 완료 상태로 변경 시 리뷰 쓰기 전 채팅방 선택 창")
    @GetMapping("/start")
    public ResponseEntity<? super StartReviewResponseDto> startReview(@RequestParam(value = "postId") Long postId, @AuthenticationPrincipal UserDetails user){
        return reviewService.startReview(postId, user);
    }

}
