package com.c_comachi.inused.global.common;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "DBE", "서버가 터졌습니다."),
    METHOD_NOT_ALLOWED(405, "C_002", "Api는 열려있으나 메소드는 사용 불가합니다."),
    INVALID_INPUT_VALUE(400, "C_003", "적절하지 않은 요청 값입니다."),
    INVALID_TYPE_VALUE(400, "C_004", "요청 값의 타입이 잘못되었습니다."),
    ENTITY_NOT_FOUND(400, "C_005", "지정한 Entity를 찾을 수 없습니다."),

    AUTH_ERROR(400, "AU_001", "인증 관련 오류가 발생했습니다."),
    DUPLICATED_EMAIL(400, "DE", "이미 존재하는 E-mail입니다."),
    UNAUTHORIZED_REDIRECT_URI(400, "AU_003", "인증되지 않은 REDIRECT_URI입니다."),
    BAD_LOGIN(400, "BL", "잘못된 아이디 또는 패스워드입니다."),
    EXPIRED_TOKEN(401, "ET", "만료된 토큰입니다."),

    USER_NOT_FOUND(400, "UN", "유저를 찾을 수 없습니다."),
    POST_NOT_FOUND(400, "PN", "글을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(400, "CN", "카테고리를 찾을 수 없습니다."),
    INQUIRY_NOT_FOUND(400, "IN", "문의 내역을 찾을 수 없습니다."),
    NOTICE_NOT_FOUND(400, "NN", "공지를 찾을 수 없습니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
