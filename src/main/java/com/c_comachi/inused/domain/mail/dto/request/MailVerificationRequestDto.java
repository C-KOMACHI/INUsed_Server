package com.c_comachi.inused.domain.mail.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MailVerificationRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String authCode;
}
