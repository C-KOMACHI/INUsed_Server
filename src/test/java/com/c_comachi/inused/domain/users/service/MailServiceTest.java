package com.c_comachi.inused.domain.users.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.c_comachi.inused.domain.users.dto.request.MailRequestDto;
import com.c_comachi.inused.domain.users.dto.request.MailVerificationRequestDto;
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

@SpringBootTest
@Slf4j
public class MailServiceTest {

    @Autowired
    MailService mailService;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("이메일 인증 보내기 실패 - 이미 가입된 이메일")
    void sendEmailFailed() {
        //given
        log.info("[existUser] email={}", "test@inu.ac.kr");
        log.info("[registerUser] email={}", "test@inu.ac.kr");

        //when
        String email = "test";
        MailRequestDto MailrequestDto = new MailRequestDto();
        MailrequestDto.setEmail(email);

        //then
        try {
            assertThat(mailService.sendEmail(MailrequestDto).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("이메일 인증 확인 실패")
    void verifiedCodeFailed() {
        //given
        log.info("[existUser] email={}", "test@inu.ac.kr");
        log.info("[registerUser] email={}", "test1@inu.ac.kr");

        //when
        String email = "test1";
        MailRequestDto mailRequestDto = new MailRequestDto();
        mailRequestDto.setEmail(email);

        MailVerificationRequestDto mailVerificationRequestDto = new MailVerificationRequestDto();
        mailVerificationRequestDto.setEmail(email);
        mailVerificationRequestDto.setAuthCode("asdsadasd");

        //then
        try{
            mailService.sendEmail(mailRequestDto);
            assertThat(  mailService.verifiedCode(mailVerificationRequestDto).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        } catch (Exception e){

        }
    }

    @BeforeEach
    void     registerOne() {
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
