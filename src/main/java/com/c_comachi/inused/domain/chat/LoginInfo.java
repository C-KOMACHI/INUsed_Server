package com.c_comachi.inused.domain.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginInfo {
    private String email;
    private String token;

    @Builder
    public LoginInfo(String email, String token){
        this.email = email;
        this.token = token;
    }
}
