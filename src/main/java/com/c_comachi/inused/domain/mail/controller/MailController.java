package com.c_comachi.inused.domain.mail.controller;

import com.c_comachi.inused.domain.mail.dto.request.MailRequestDto;
import com.c_comachi.inused.domain.mail.dto.request.MailVerificationRequestDto;
import com.c_comachi.inused.domain.mail.dto.response.EmailCheckResponseDto;
import com.c_comachi.inused.domain.mail.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Tag(name = "Mail" , description = "Mail 인증 API 구현")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/emails")
public class MailController {

    private final MailService mailService;

    @Operation( summary = "mail 인증 보내기")
    @PostMapping("/verification-requests")
    public ResponseEntity<? super EmailCheckResponseDto> EmailCheck(@RequestBody @Valid MailRequestDto requestBody) throws MessagingException, UnsupportedEncodingException {
        ResponseEntity<? super EmailCheckResponseDto> response = mailService.sendEmail(requestBody);
        return response;
    }

    @Operation(summary = "mail 인증 확인")
    @GetMapping("/verifications")
    public ResponseEntity<? super EmailCheckResponseDto> verificationEmail(@RequestBody @Valid MailVerificationRequestDto requestBody) {
        ResponseEntity<? super EmailCheckResponseDto> response = mailService.verifiedCode(requestBody);
        return response;
    }

}
