package com.c_comachi.inused.domain.wish.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DeleteWishResponseDto extends ResponseDto {
    private DeleteWishResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeleteWishResponseDto> success() {
        DeleteWishResponseDto result = new DeleteWishResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
