package com.c_comachi.inused.domain.users.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.c_comachi.inused.domain.mail.dto.request.MailRequestDto;
import com.c_comachi.inused.domain.mail.dto.request.MailVerificationRequestDto;
import com.c_comachi.inused.domain.mail.service.MailService;
import com.c_comachi.inused.global.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@Slf4j
public class MailControllerTest {
    @Autowired
    MailService mailService;
    @Autowired
    RedisService redisService;


    @Test
    @DisplayName("이메일 인증 보내기")
    void sendEmail() {
        //given
        log.info("[existUser] email={}", "test@inu.ac.kr");
        log.info("[registerUser] email={}", "mjk8087@inu.ac.kr");

        //when
        String email = "mjk8087";
        MailRequestDto MailrequestDto = new MailRequestDto();
        MailrequestDto.setEmail(email);

        //then
        try {
            assertThat(mailService.sendEmail(MailrequestDto).getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

//    @Test
//    @DisplayName("이메일 인증 코드 확인")
//    void verifiedEmailCode() {
//
//        //given
//        log.info("[existUser] email={}", "test@inu.ac.kr");
//        log.info("[registerUser] email={}", "mjk8087@inu.ac.kr");
//
//        try {
//            //when
//            String email = "mjk8087";
//            MailRequestDto MailrequestDto = new MailRequestDto();
//            MailrequestDto.setEmail(email);
//            mailService.sendEmail(MailrequestDto);
//
//            MailVerificationRequestDto mailVerificationRequestDto = new MailVerificationRequestDto();
//            mailVerificationRequestDto.setEmail(email);
//            mailVerificationRequestDto.setAuthCode(redisService.getValues(email));
//
//            //then
//            assertThat(mailService.verifiedCode(mailVerificationRequestDto).getStatusCode()).isEqualTo(HttpStatus.OK);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
