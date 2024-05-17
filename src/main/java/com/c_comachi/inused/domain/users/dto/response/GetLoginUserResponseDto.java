package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.domain.users.entity.Authority;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetLoginUserResponseDto extends ResponseDto  {

    private final String email;
    private final String nickname;
    private final String profileImage;
    private final Double fireTemperature;
    private final Authority authority;

    private GetLoginUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
        this.fireTemperature = userEntity.getFireTemperature();
        this.authority = userEntity.getAuthority();
    }

    public static ResponseEntity<GetLoginUserResponseDto> success(UserEntity user) {
        GetLoginUserResponseDto result = new GetLoginUserResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
