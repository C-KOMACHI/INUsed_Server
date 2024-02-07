package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class EmailCheckResponseDto extends ResponseDto {
    private String authCode;

    private EmailCheckResponseDto (String authCode) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.authCode = authCode;
    }

    public static ResponseEntity<EmailCheckResponseDto> success(String authCode){
        EmailCheckResponseDto result = new EmailCheckResponseDto(authCode);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
