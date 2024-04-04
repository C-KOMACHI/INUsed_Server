package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PasswordFindResponseDto extends ResponseDto{
    private PasswordFindResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PasswordFindResponseDto> success(){
        PasswordFindResponseDto result = new PasswordFindResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedEmail() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> samePassword() {
        ResponseDto result = new ResponseDto(ResponseCode.SAME_PASSWORD, ResponseMessage.SAME_PASSWORD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
