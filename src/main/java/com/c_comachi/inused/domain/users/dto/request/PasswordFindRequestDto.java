package com.c_comachi.inused.domain.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordFindRequestDto {
    @Email
    private String email;
    private String password;

}
