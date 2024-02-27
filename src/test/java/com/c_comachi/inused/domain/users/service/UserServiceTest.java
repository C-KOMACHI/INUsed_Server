package com.c_comachi.inused.domain.users.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import com.c_comachi.inused.domain.users.dto.request.LoginRequestDto;
import com.c_comachi.inused.domain.users.dto.request.RegisterRequestDto;
import com.c_comachi.inused.domain.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;


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
    @Test
    @DisplayName("회원가입 실패 - 이메일 중복")
    void registerEmailDuplicate() {
        // when
        log.info("[existUser] email={}, password={}", "test@inu.ac.kr", "test");
        log.info("[addUser] email={}, password={}", "test@inu.ac.kr", "test1");

        //given
        RegisterRequestDto addUser = new RegisterRequestDto("test", "test1", "test1234");

        //then
        assertThat(authService.register(addUser).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("회원가입 실패 - 닉네임 중복")
    void registerNicknameDuplicate() {
        // when
        log.info("[existUser] email={}, password={}", "test@inu.ac.kr", "test");
        log.info("[addUser] email={}, password={}", "test1@inu.ac.kr", "test");

        //given
        RegisterRequestDto addUser = new RegisterRequestDto("test1", "test", "test1234");

        //then
        assertThat(authService.register(addUser).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFailed() {
        log.info("[existUser] email={}, password={}", "test@inu.ac.kr", "test1234");
        log.info("[loginUser] email={}, password={}", "test1234@inu.ac.kr", "test1234");

        LoginRequestDto loginUser = new LoginRequestDto("test@inu.ac.kr", "test12345678");

        assertThat(authService.login(loginUser).getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}
