package com.potatoes.potential.common.dto;

import com.potatoes.potential.common.code.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private StatusEnum status;
    private String message;
    private T data;
}
