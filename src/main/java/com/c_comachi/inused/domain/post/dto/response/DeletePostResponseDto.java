package com.c_comachi.inused.domain.post.dto.response;

import com.c_comachi.inused.domain.post.entity.CategoryEntity;
import com.c_comachi.inused.domain.post.entity.PostEntity;
import com.c_comachi.inused.domain.post.entity.Status;
import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class DeletePostResponseDto extends ResponseDto {
    private DeletePostResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeletePostResponseDto> success() {
        DeletePostResponseDto result = new DeletePostResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
