package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.LoginRequestDto;
import com.c_comachi.inused.domain.users.dto.request.NicknameRequestDto;
import com.c_comachi.inused.domain.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.domain.users.dto.request.TokenRequestDto;
import com.c_comachi.inused.domain.users.dto.request.*;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.dto.response.LogoutResponseDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.domain.users.dto.response.ReissueResponseDto;
import com.c_comachi.inused.domain.users.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.c_comachi.inused.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth 관련 api 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = RegisterResponseDto.class))),
    })
    @Operation(summary = "회원 가입")
    @PostMapping("/register")
    public ResponseEntity<? super RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto requestBody) {
        ResponseEntity<? super RegisterResponseDto> response = authService.register(requestBody);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
    })
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<? super LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestBody) {
        ResponseEntity<? super LoginResponseDto> response = authService.login(requestBody);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = ReissueResponseDto.class))),
    })
    @Operation(summary = "토큰 재발급")
    @PatchMapping("/password-find")
    public ResponseEntity<ResponseDto> passwordFind(@RequestBody @Valid PasswordFindRequestDto passwordFindRequestDto){
        authService.passwordFinder(passwordFindRequestDto);
        return ResponseDto.suc();
    }
    
    @PatchMapping("/reissue")
    public ResponseEntity<? super ReissueResponseDto> reissue(@RequestBody @Valid TokenRequestDto requestBody) {
        ResponseEntity<? super ReissueResponseDto> response = authService.reissue(requestBody);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = LogoutResponseDto.class))),
    })
    @Operation(summary = "로그아웃")
    @PatchMapping("/logout")
    public ResponseEntity<? super LogoutResponseDto> logout(@RequestBody @Valid TokenRequestDto requestBody) {
        ResponseEntity<? super LogoutResponseDto> response = authService.logout(requestBody);
        return response;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = RegisterResponseDto.class))),
    })
    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/nickname-check")
    public ResponseEntity<? super RegisterResponseDto> nicknameCheck(@RequestBody @Valid NicknameRequestDto requestBody) {
        ResponseEntity<? super RegisterResponseDto> response = authService.nicknameCheck(requestBody);
        return response;
    }

}
