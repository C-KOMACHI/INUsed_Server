package com.c_comachi.inused.domain.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PasswordFindRequestDto {
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;

}
