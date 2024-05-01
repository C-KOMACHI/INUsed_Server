package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.request.*;
import com.c_comachi.inused.domain.users.dto.response.LogoutResponseDto;
import com.c_comachi.inused.domain.users.dto.response.PasswordFindResponseDto;
import com.c_comachi.inused.domain.users.dto.response.ReissueResponseDto;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.dto.response.RegisterResponseDto;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super RegisterResponseDto> register(RegisterRequestDto requestDto);
    ResponseEntity<? super LoginResponseDto> login(LoginRequestDto loginRequestDto);
    ResponseEntity<? super ReissueResponseDto> reissue(TokenRequestDto tokenRequestDto);
    ResponseEntity<? super LogoutResponseDto> logout(TokenRequestDto tokenRequestDto);
    ResponseEntity<? super RegisterResponseDto> nicknameCheck(String  nickname);

    ResponseEntity<? super PasswordFindResponseDto> passwordFinder(PasswordFindRequestDto passwordFindRequestDto);
}
