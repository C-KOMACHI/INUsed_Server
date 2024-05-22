package com.c_comachi.inused.domain.firebase.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class FirebaseNotificationResponseDto extends ResponseDto {
    private FirebaseNotificationResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<FirebaseNotificationResponseDto> success() {
        FirebaseNotificationResponseDto result = new FirebaseNotificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<FirebaseNotificationResponseDto> failure() {
        FirebaseNotificationResponseDto result = new FirebaseNotificationResponseDto();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}

