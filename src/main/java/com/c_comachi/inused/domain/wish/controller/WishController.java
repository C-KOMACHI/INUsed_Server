package com.c_comachi.inused.domain.wish.controller;

import com.c_comachi.inused.domain.post.dto.response.CreatePostResponseDto;
import com.c_comachi.inused.domain.wish.dto.request.CreateWishRequestDto;
import com.c_comachi.inused.domain.wish.dto.response.GetWishesResponseDto;
import com.c_comachi.inused.domain.wish.service.WishService;
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

@Tag(name = "Wish" , description = "Wish 관련 API 모음")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishes")
public class WishController {

    private final WishService wishService;

    @Operation(summary = "관심 등록/삭제")
    @PostMapping("/click")
    public ResponseEntity<ResponseDto> addWish(@RequestBody @Valid CreateWishRequestDto requestsDto, @AuthenticationPrincipal UserDetails user) {
        ResponseEntity<ResponseDto> response = wishService.addWish(requestsDto, user);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(schema = @Schema(implementation = GetWishesResponseDto.class))),
    })
    @Operation(summary = "관심 조회")
    @GetMapping("")
    public ResponseEntity<? super GetWishesResponseDto> getWishes(@AuthenticationPrincipal UserDetails user) {
        ResponseEntity<? super GetWishesResponseDto> response = wishService.getWish(user);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "생성 성공", content = @Content(schema = @Schema(implementation = GetWishesResponseDto.class))),
    })
    @Operation(summary = "타인 관심 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<? super GetWishesResponseDto> getUserWishes(@PathVariable(value = "userId") Long userId, @AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity<? super GetWishesResponseDto> response = wishService.getUserWishes(userId, userDetails);
        return response;
    }

}
