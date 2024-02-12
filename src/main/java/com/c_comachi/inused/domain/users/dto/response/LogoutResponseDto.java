package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;

@Getter
public class LogoutResponseDto extends ResponseDto {
    private LogoutResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
}
