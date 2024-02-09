package com.c_comachi.inused.domain.users.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MailVerificationRequestDto {
    private String email;
    private String authCode;
}
