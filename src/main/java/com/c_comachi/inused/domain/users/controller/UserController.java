package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.UserEditRequestDto;
import com.c_comachi.inused.domain.users.dto.response.GetUserResponseDto;
import com.c_comachi.inused.domain.users.service.UserService;
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

@Tag(name = "User" , description = "User 관련 api 모음")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetUserResponseDto.class))),
    })
    @Operation(summary = "내 정보 조회", description = "header 에 토큰 넣어서")
    @GetMapping("/get")
    public ResponseEntity<? super GetUserResponseDto> getUser(@AuthenticationPrincipal UserDetails user) {
        return userService.getLoginUser(user.getUsername());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = GetUserResponseDto.class))),
    })
    @Operation(summary = "특정 회원 조회")
    @GetMapping("/get/{userId}")
    public ResponseEntity<? super GetUserResponseDto> getUser(@PathVariable(value = "userId") Long userId) {
        return userService.getUser(userId);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "회원 수정", description = "header 에 토큰 넣어서")
    @PatchMapping("/edit")
    public ResponseEntity<ResponseDto> editUser(@AuthenticationPrincipal UserDetails user, @RequestBody @Valid UserEditRequestDto requestDto) {
        userService.editUser(user.getUsername(), requestDto.toUserEditInfo());
        return ResponseDto.suc();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    @Operation(summary = "회원 탈퇴", description = "header 에 토큰 넣어서")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(@AuthenticationPrincipal UserDetails user) {
        userService.deleteUser(user.getUsername());
        return ResponseDto.suc();
    }
}
