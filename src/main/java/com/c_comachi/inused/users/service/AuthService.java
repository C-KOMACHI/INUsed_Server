package com.c_comachi.inused.users.service;

import com.c_comachi.inused.users.dto.response.ReissueResponseDto;
import com.c_comachi.inused.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.users.dto.request.LoginRequestDto;
import com.c_comachi.inused.users.dto.request.TokenRequestDto;
import com.c_comachi.inused.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.users.dto.response.RegisterResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super RegisterResponseDto> register(RegisterRequestDto requestDto);
    ResponseEntity<? super LoginResponseDto> login(LoginRequestDto loginRequestDto);
    ResponseEntity<? super ReissueResponseDto> reissue(TokenRequestDto tokenRequestDto);
}
