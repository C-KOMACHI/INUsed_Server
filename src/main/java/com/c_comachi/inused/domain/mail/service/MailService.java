package com.c_comachi.inused.domain.mail.service;

import com.c_comachi.inused.domain.mail.dto.request.MailRequestDto;
import com.c_comachi.inused.domain.mail.dto.request.MailVerificationRequestDto;
import com.c_comachi.inused.domain.mail.dto.response.EmailCheckResponseDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface MailService {
    public void createCode();
    public MimeMessage createEmailForm(String email);
    public ResponseEntity<? super EmailCheckResponseDto> sendEmail(MailRequestDto requestBody) throws MessagingException, UnsupportedEncodingException;
    public  ResponseEntity<? super EmailCheckResponseDto> verifiedCode(String email, String authCode);

}
