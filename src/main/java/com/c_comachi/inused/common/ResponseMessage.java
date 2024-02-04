package com.c_comachi.inused.common;

public interface ResponseMessage {
    // Http Status 200
    String SUCCESS = "Success";

    // Http Status 400
    String VALIDATION_FAILED = "Validation failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplicate nickname";
    String  UNHEALTHY_NICKNAME = "Unhealthy nickname";
    String NOT_EXISTED_USER = "Not Existed user";
    String MISMATCHED_TOKEN = "Mismatched Token";

    // Http Status 401
    String LOGIN_FAILED = "Login failed";
    String AUTHORIZATION_FAILED = "Authorization failed";
    String LOGGED_OUT_USER = "User has already Logged out";

    // Http Status 403
    String NO_PERMISSION = "No permission";



    // Http Status 500
    String DATABASE_ERROR = "Database Error";
}
