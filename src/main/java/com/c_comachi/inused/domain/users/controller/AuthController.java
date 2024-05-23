package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.LoginRequestDto;
import com.c_comachi.inused.domain.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.domain.users.dto.request.TokenRequestDto;
import com.c_comachi.inused.domain.users.dto.request.*;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.dto.response.LogoutResponseDto;
import com.c_comachi.inused.domain.users.dto.response.PasswordFindResponseDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.domain.users.dto.response.ReissueResponseDto;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import com.c_comachi.inused.domain.users.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth 관련 api 모음")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = RegisterResponseDto.class))),
    })
    @Operation(summary = "회원 가입")
    @PostMapping("/register")
    public ResponseEntity<? super RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto requestBody) {
        return authService.register(requestBody);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
    })
    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<? super LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestBody, HttpServletResponse response) {
        return authService.login(requestBody, response);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = PasswordFindResponseDto.class))),
    })
    @Operation(summary = "비밀번호 찾기")
    @PatchMapping("/password-find")
    public ResponseEntity<? super PasswordFindResponseDto> passwordFind(@RequestBody @Valid PasswordFindRequestDto passwordFindRequestDto){
        return authService.passwordFinder(passwordFindRequestDto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = ReissueResponseDto.class))),
    })
    @Operation(summary = "토큰 재발급", description = "header 에도 토큰 부탁드려요")
    @PatchMapping("/reissue")
    public ResponseEntity<? super ReissueResponseDto> reissue(HttpServletRequest request, HttpServletResponse response,
                                                              @RequestHeader(name = TokenProvider.REFRESH_HEADER, required = false) String refreshToken) {
        return authService.reissue(request, response, refreshToken);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = LogoutResponseDto.class))),
    })
    @Operation(summary = "로그아웃", description = "header 에도 토큰 부탁드려요")
    @PatchMapping("/logout")
    public ResponseEntity<? super LogoutResponseDto> logout(@RequestBody @Valid TokenRequestDto requestBody, @AuthenticationPrincipal UserDetails user) {
        return authService.logout(requestBody);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "인증 보내기 성공", content = @Content(schema = @Schema(implementation = RegisterResponseDto.class))),
    })
    @Operation(summary = "닉네임 중복 확인")
    @GetMapping("/nickname-check")
    public ResponseEntity<? super RegisterResponseDto> nicknameCheck(@RequestParam(value = "nickname") String nickname) {
        return authService.nicknameCheck(nickname);
    }

}
