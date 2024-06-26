package com.c_comachi.inused.global.common;

public interface ResponseCode {

    // Http Status 200
    String SUCCESS = "SU";

    // Http Status 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE ";
    String DUPLICATE_NICKNAME = "DN";
    String UNHEALTHY_NICKNAME = "UN";
    String NOT_EXISTED_USER = "NU";
    String BAD_EMAIL = "BE";
    String MISMATCHED_TOKEN = "MT";
    String SAME_PASSWORD = "SP";
    String NOT_EXISTED_NOTICE = "NN";
    String NOT_EXISTED_INQUIRY = "NI";
    String TOO_FAST_REPOSTING = "TR";

    // Http Status 401
    String LOGIN_FAILED = "LF";
    String AUTHORIZATION_FAILED = "AF";
    String LOGGED_OUT_USER = "LU";

    // Http Status 403
    String NO_PERMISSION = "NP";



    // Http Status 500
    String DATABASE_ERROR = "DBE";
}
