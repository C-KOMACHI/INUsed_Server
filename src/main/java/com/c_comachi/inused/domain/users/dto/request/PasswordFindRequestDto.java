package com.c_comachi.inused.domain.users.dto.request;

import lombok.Data;

@Data
public class PasswordFindRequestDto {
    private String email;
    private String password;

}
