package com.c_comachi.inused.domain.mail.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MailVerificationRequestDto {
    private String email;
    private String authCode;
}
