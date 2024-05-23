package com.c_comachi.inused.global.exception;

import com.c_comachi.inused.global.common.ErrorCode;

public class InvalidValueException extends BusinessException{

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}
