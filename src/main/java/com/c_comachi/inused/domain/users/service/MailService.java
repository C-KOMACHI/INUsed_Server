package com.c_comachi.inused.domain.users.service;

import com.c_comachi.inused.domain.users.dto.response.EmailCheckResponseDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface MailService {
    public void createCode();
    public MimeMessage createEmailForm(String email);
    public ResponseEntity<? super EmailCheckResponseDto> sendEmail(String email) throws MessagingException, UnsupportedEncodingException;
    public boolean isValidEmailAddress(String email);
}
