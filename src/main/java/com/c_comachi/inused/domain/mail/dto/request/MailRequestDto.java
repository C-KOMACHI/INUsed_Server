package com.c_comachi.inused.domain.mail.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class MailRequestDto {
    private String email;
}
