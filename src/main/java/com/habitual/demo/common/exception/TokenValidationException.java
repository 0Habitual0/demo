package com.habitual.demo.common.exception;

import lombok.Getter;

@Getter
public class TokenValidationException extends RuntimeException {

    private Integer errorCode;

    public TokenValidationException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
