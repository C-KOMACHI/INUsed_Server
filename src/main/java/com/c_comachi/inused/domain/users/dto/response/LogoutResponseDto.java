package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class LogoutResponseDto extends ResponseDto {
    private LogoutResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<LogoutResponseDto> success(){
        LogoutResponseDto result = new LogoutResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
