package com.c_comachi.inused.domain.users.controller;

import com.c_comachi.inused.domain.users.dto.request.MailRequestDto;
import com.c_comachi.inused.domain.users.dto.request.MailVerificationRequestDto;
import com.c_comachi.inused.domain.users.dto.response.EmailCheckResponseDto;
import com.c_comachi.inused.domain.users.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emails")
public class MailController {

    private final MailService mailService;

    @PostMapping("/verification-requests")
    public ResponseEntity<? super EmailCheckResponseDto> EmailCheck(@RequestBody @Valid MailRequestDto requestBody) throws MessagingException, UnsupportedEncodingException {
        ResponseEntity<? super EmailCheckResponseDto> response = mailService.sendEmail(requestBody.getEmail());
        return response;
    }
    @GetMapping("/verifications")
    public ResponseEntity<? super EmailCheckResponseDto> verificationEmail(@RequestBody @Valid MailVerificationRequestDto requestBody) {
        ResponseEntity<? super EmailCheckResponseDto> response = mailService.verifiedCode(requestBody);
        return response;
    }

}
