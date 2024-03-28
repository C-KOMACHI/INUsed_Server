package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.UserEditRequestDto;
import com.c_comachi.inused.domain.users.dto.response.GetLoginUserResponseDto;
import com.c_comachi.inused.domain.users.service.UserService;
import com.c_comachi.inused.global.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User" , description = "User 관련 api 명세서")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 조회", description = "header 에 토큰 넣어서")
    @GetMapping("/get")
    public ResponseEntity<? super GetLoginUserResponseDto> getUser(Authentication authentication) {
        ResponseEntity<? super GetLoginUserResponseDto> response = userService.getLoginUser(authentication.getName());
        return response;
    }


    @Operation(summary = "회원 수정", description = "header 에 토큰 넣어서")
    @PatchMapping("/edit")
    public ResponseEntity<ResponseDto> editUser(Authentication authentication, @RequestBody @Valid UserEditRequestDto requestDto) {
        userService.editUser(authentication.getName(), requestDto.toUserEditInfo());
        return ResponseDto.suc();
    }


    @Operation(summary = "회원 탈퇴", description = "header 에 토큰 넣어서")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return ResponseDto.suc();
    }
}
