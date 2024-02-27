package com.c_comachi.inused.domain.users.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.c_comachi.inused.domain.users.dto.request.LoginRequestDto;
import com.c_comachi.inused.domain.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.domain.users.dto.request.TokenRequestDto;
import com.c_comachi.inused.domain.users.dto.response.LoginResponseDto;
import com.c_comachi.inused.domain.users.jwt.TokenProvider;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import com.c_comachi.inused.domain.users.service.AuthService;
import com.c_comachi.inused.global.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@Slf4j
class AuthControllerTest{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    RedisService redisService;


    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() throws Exception {
        //given
        log.info("[existUser] email={}, nickname ={}, password={}", "test@inu.ac.kr","test","test1234");
        log.info("[addUser] email={}, nickname ={}, password={}", "test1@inu.ac.kr", "test1", "test1234");

        //when
        RegisterRequestDto addUser = new RegisterRequestDto("test1", "test1", "test1234");

        //then
        assertThat(authService.register(addUser).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        //given
        log.info("[existUser] email={}, password={}", "test@inu.ac.kr", "test1234");
        log.info("[loginUser] email={}, password={}", "test@inu.ac.kr", "test1234");

        //when
        LoginRequestDto loginUser = new LoginRequestDto("test@inu.ac.kr", "test1234");


        //then
        assertThat(authService.login(loginUser).getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    @DisplayName("토큰 재발급 성공")
    void reissue() {
        //given
        log.info("[loginUser] email={}, password={}", "test@inu.ac.kr", "test1234");
        log.info("[ReissueUser] email={}, password={}", "test@inu.ac.kr", "test1234");

        //when

        LoginRequestDto loginUser = new LoginRequestDto("test@inu.ac.kr", "test1234");
        ResponseEntity<? super LoginResponseDto> login = authService.login(loginUser);
        LoginResponseDto loginResponseDto = (LoginResponseDto) login.getBody();
        TokenRequestDto tokenRequestDto = new TokenRequestDto(loginResponseDto.getAccessToken(), loginResponseDto.getRefreshToken());

        //then
        assertThat(authService.reissue(tokenRequestDto).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("로그아웃 성공")
    void logout() {
        //given
        log.info("[loginUser] email={}, password={}", "test@inu.ac.kr", "test1234");
        log.info("[logoutUser] email={}, password={}", "test@inu.ac.kr", "test1234");

        //when
        LoginRequestDto loginUser = new LoginRequestDto("test@inu.ac.kr", "test1234");
        ResponseEntity<? super LoginResponseDto> login = authService.login(loginUser);
        LoginResponseDto loginResponseDto = (LoginResponseDto) login.getBody();
        TokenRequestDto tokenRequestDto = new TokenRequestDto(loginResponseDto.getAccessToken(), loginResponseDto.getRefreshToken());

        //then
        assertThat(authService.logout(tokenRequestDto).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @BeforeEach
    void registerOne() {
        log.info("--- [beforeEach] add testUser ---");
        userRepository.deleteAll();

        RegisterRequestDto addUser = new RegisterRequestDto("test", "test", "test1234");
        authService.register(addUser);
    }

    @AfterEach
    void afterEach() {
        log.info("--- [afterEach] remove testUser ---");
        userRepository.deleteAll();
    }
}
