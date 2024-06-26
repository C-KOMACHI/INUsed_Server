package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.domain.users.dto.UserInfo;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserResponseDto extends ResponseDto  {

    private final UserInfo user;


    private GetUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.user = new UserInfo(userEntity);
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity user) {
        GetUserResponseDto result = new GetUserResponseDto(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
