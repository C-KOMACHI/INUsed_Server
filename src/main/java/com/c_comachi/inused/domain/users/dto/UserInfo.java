package com.c_comachi.inused.domain.users.dto;

import com.c_comachi.inused.domain.users.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserInfo {
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;
    private Integer fireTemperature;

    public UserInfo(UserEntity user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profileImage = user.getProfileImage();
        this.fireTemperature = user.getFireTemperature();
    }

}
