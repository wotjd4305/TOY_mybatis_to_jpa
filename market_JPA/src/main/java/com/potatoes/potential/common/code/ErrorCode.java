package com.potatoes.potential.common.code;

public enum ErrorCode {

    ARTICLE_NOT_FOUND("ARTICLE_NOT_FOUND", 404),
    ;

    private final String message;
    private final Integer code;

    ErrorCode(final String message, final Integer code) {
        this.message = message;
        this.code = code;
    }

    public String message() {
        return this.message;
    }
}
