package com.c_comachi.inused.global.exception;

import com.c_comachi.inused.global.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> validationExceptionHandler(MethodArgumentNotValidException  exception) {

        return ResponseDto.validationFailed();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex) {
        // Handle the exception as needed
        return ResponseDto.validationFailed();
    }
}
