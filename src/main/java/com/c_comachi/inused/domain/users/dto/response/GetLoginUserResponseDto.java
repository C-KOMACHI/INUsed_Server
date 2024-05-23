package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.domain.users.dto.UserInfo;
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

    private final UserInfo user;


    private GetLoginUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.user = new UserInfo(userEntity);
    }

    public static ResponseEntity<GetLoginUserResponseDto> success(UserEntity user) {
        GetLoginUserResponseDto result = new GetLoginUserResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
