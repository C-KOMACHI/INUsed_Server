package com.c_comachi.inused.dto.response.auth;

import com.c_comachi.inused.common.ResponseCode;
import com.c_comachi.inused.common.ResponseMessage;
import com.c_comachi.inused.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class LoginResponseDto extends ResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;

    private LoginResponseDto(TokenDto tokenDto){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.accessToken = tokenDto.getAccessToken();
        this.refreshToken = tokenDto.getRefreshToken();
        this.accessTokenExpiresIn = tokenDto.getAccessTokenExpiresIn();
    }

    public static ResponseEntity<LoginResponseDto> success(TokenDto tokenDto) {
        LoginResponseDto result = new LoginResponseDto(tokenDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> loginFailed() {
        ResponseDto result = new ResponseDto(ResponseCode.LOGIN_FAILED, ResponseMessage.LOGIN_FAILED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
