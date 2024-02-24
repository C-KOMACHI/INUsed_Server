package com.c_comachi.inused.domain.users.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.c_comachi.inused.domain.users.dto.request.MailRequestDto;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    @DisplayName("이메일 인증 보내기 실패 - 이미 가입된 이메일")
    void sendEmailFailed() {
        //when
        log.info("[existUser] email={}", "test@inu.ac.kr");
        log.info("[registerUser] email={}", "test@inu.ac.kr");

        //given
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

}
