package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.LoginRequestDto;
import com.c_comachi.inused.domain.users.dto.request.MailRequestDto;
import com.c_comachi.inused.domain.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.domain.users.dto.request.TokenRequestDto;
import com.c_comachi.inused.domain.users.dto.response.EmailCheckResponseDto;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.domain.users.dto.response.ReissueResponseDto;
import com.c_comachi.inused.domain.users.service.AuthService;
import com.c_comachi.inused.domain.users.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<? super RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto requestBody) {
        ResponseEntity<? super RegisterResponseDto> response = authService.register(requestBody);
        return response;
    }
    @PostMapping("/login")
    public ResponseEntity<? super LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestBody) {
        ResponseEntity<? super LoginResponseDto> response = authService.login(requestBody);
        return response;
    }

    @PostMapping("/reissue")
    public ResponseEntity<? super ReissueResponseDto> reissue(@RequestBody @Valid TokenRequestDto requestBody) {
        ResponseEntity<? super ReissueResponseDto> response = authService.reissue(requestBody);
        return response;
    }

}
