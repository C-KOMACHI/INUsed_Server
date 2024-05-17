package com.c_comachi.inused.domain.users.dto.response;

import com.c_comachi.inused.global.common.ResponseCode;
import com.c_comachi.inused.global.common.ResponseMessage;
import com.c_comachi.inused.global.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ReissueResponseDto extends ResponseDto {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
    private final Long accessTokenExpiresIn;

    private ReissueResponseDto(TokenDto tokenDto){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.grantType = tokenDto.getGrantType();
        this.accessToken = tokenDto.getAccessToken();
        this.refreshToken = tokenDto.getRefreshToken();
        this.accessTokenExpiresIn = tokenDto.getAccessTokenExpiresIn();
    }

    public static ResponseEntity<ReissueResponseDto> success(TokenDto tokenDto) {
        ReissueResponseDto result = new ReissueResponseDto(tokenDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> mismatchedToken() {
        ResponseDto result = new ResponseDto(ResponseCode.MISMATCHED_TOKEN, ResponseMessage.MISMATCHED_TOKEN);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> loggedOutUser() {
        ResponseDto result = new ResponseDto(ResponseCode.LOGGED_OUT_USER, ResponseMessage.LOGGED_OUT_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
