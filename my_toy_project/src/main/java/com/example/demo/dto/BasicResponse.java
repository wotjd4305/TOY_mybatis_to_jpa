package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse<T> {
    @ApiModelProperty(value = "isSuccess", position = 1)
    public boolean isSuccess;
    @ApiModelProperty(value = "message", position = 2)
    public String message;
    @ApiModelProperty(value = "data", position = 3)
    public T data;
}