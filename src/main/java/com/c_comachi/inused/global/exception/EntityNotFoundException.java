package com.c_comachi.inused.global.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
