package com.c_comachi.inused.global.exception.common;

import lombok.Getter;

@Getter
public enum StatusEnum {
    BAD_REQUEST(400, "BAD_REQUEST"),
    VOTE_TYPE_NOT_MATCH(400, "VOTE_TYPE_NOT_MATCH"),
    VALIDATION_FAILED(400, "Validation failed"),
    DUPLICATE_EMAIL(400, "Duplicate nickname"),
    NOT_EXISTED_USER(400, "Not Existed user"),
    MISMATCHED_TOKEN(400, "Mismatched Token"),
    LOGIN_FAILED(401, "Login failed"),
    AUTHORIZATION_FAILED(401, "Authorization failed"),
    LOGGED_OUT_USER(401, "User has already Logged out"),
    NO_PERMISSION(403, "No Permission"),
    DATABASE_ERROR(500, "Database Error");



    private final int code;
    private final String message;

    private StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
