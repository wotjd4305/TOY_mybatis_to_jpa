package com.potatoes.potential.common.dto;

import org.springframework.http.HttpStatus;

public record BaseResponseEntity<T>(Integer resultCode, T data, String message) {

    public BaseResponseEntity(T data) {
        this(HttpStatus.OK.value(), data, HttpStatus.OK.toString());
    }

    public BaseResponseEntity(Integer resultCode, T data, String message) {
        this.resultCode = resultCode;
        this.data = data;
        this.message = message;
    }
}
