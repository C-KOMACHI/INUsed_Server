package com.c_comachi.inused.dto.response.auth;

import com.c_comachi.inused.common.ResponseCode;
import com.c_comachi.inused.common.ResponseMessage;
import com.c_comachi.inused.dto.response.ResponseDto;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class RegisterResponseDto extends ResponseDto {

    private RegisterResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<RegisterResponseDto> success(){
        RegisterResponseDto result = new RegisterResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateEmail() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> unhealthyNickname() {
        ResponseDto result = new ResponseDto(ResponseCode.UNHEALTHY_NICKNAME, ResponseMessage.UNHEALTHY_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
