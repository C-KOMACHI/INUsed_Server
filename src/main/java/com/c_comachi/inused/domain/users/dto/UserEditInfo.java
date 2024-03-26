package com.c_comachi.inused.domain.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEditInfo {
    String profileImage;
    String nickname;

    @Builder
    public UserEditInfo(String profileImage, String nickname){
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}
