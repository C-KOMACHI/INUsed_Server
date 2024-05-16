package com.c_comachi.inused.global.exception;

import com.c_comachi.inused.global.common.ErrorCode;

public class AuthenticationException extends BusinessException{

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
