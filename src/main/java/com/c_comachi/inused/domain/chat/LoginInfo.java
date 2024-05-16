package com.c_comachi.inused.domain.chat;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginInfo {
    private String nickname;
    private String token;

    @Builder
    public LoginInfo(String nickname, String token){
        this.nickname = nickname;
        this.token = token;
    }
}
