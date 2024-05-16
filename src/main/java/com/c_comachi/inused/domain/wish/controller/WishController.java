package com.c_comachi.inused.domain.wish.controller;

import com.c_comachi.inused.domain.wish.dto.request.CreateWishRequestDto;
import com.c_comachi.inused.domain.wish.service.WishService;
import com.c_comachi.inused.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "관심 등록")
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addWish(@RequestBody @Valid CreateWishRequestDto requestsDto, @AuthenticationPrincipal UserDetails user) {
        ResponseEntity<ResponseDto> response = wishService.addWish(requestsDto, user);
        return response;
    }

}
