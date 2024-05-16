package com.c_comachi.inused.global.exception;

import com.c_comachi.inused.global.common.ErrorCode;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
