package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.users.entity.UserEntity;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RePostResponseDto extends ResponseDto {

    public RePostResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public static ResponseEntity<RePostResponseDto> success() {
        RePostResponseDto result = new RePostResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> fastRePosting(){
        ResponseDto result = new ResponseDto(ResponseCode.TOO_FAST_REPOSTING, ResponseMessage.TOO_FAST_REPOSTING);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
