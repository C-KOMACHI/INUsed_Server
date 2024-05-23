package com.c_comachi.inused.domain.keyword.controller;


import com.c_comachi.inused.domain.keyword.dto.request.CreateKeywordRequestDto;
import com.c_comachi.inused.domain.keyword.dto.request.DeleteKeywordRequestDto;
import com.c_comachi.inused.domain.keyword.dto.response.GetKeyWordResponseDto;
import com.c_comachi.inused.domain.keyword.dto.response.GetKeywordPostResponseDto;
import com.c_comachi.inused.domain.keyword.service.KeywordService;
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

@Tag(name = "Keyword" , description = "Keyword 관련 API 모음")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "추가 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "키워드 추가")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createKeyword(@RequestBody @Valid CreateKeywordRequestDto requestsDto, @AuthenticationPrincipal UserDetails user) {
        return keywordService.createKeyword(requestsDto, user);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "키워드 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteKeyword(@RequestBody @Valid DeleteKeywordRequestDto requestsDto) {
        return keywordService.deleteKeyword(requestsDto);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetKeyWordResponseDto.class))),
    })
    @Operation(summary = "키워드 조회")
    @GetMapping("")
    public ResponseEntity<? super GetKeyWordResponseDto> getKeyword(@AuthenticationPrincipal UserDetails userDetails) {
        return keywordService.getKeyword(userDetails);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetKeyWordResponseDto.class))),
    })
    @Operation(summary = "키워드 해당 포스트 조회")
    @GetMapping("/get-post")
    public ResponseEntity<? super GetKeywordPostResponseDto> getKeywordPost(@AuthenticationPrincipal UserDetails userDetails) {
        return keywordService.getKeywordPost(userDetails);
    }

}
