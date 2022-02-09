package com.dev.hlog.util.exeption;

import com.dev.hlog.model.ErrorCode;
import lombok.Data;


@Data
public class ErrorResponse {
    private String success;
    private int httpStatus;
    private String errorMessage;
    private String errorCode;

    public ErrorResponse(ErrorCode errorCode){
        this.success = errorCode.getSuccess();
        this.httpStatus = errorCode.getHttpStatus();
        this.errorMessage = errorCode.getErrorMessage();
        this.errorCode = errorCode.getErrorCode();
    }
}
