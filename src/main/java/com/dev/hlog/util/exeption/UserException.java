package com.dev.hlog.util.exeption;

import com.dev.hlog.model.ErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
    private final ErrorCode errorCode;

    public UserException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}