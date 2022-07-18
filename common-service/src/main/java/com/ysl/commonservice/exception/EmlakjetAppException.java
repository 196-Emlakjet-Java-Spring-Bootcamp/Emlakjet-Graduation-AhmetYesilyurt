package com.ysl.commonservice.exception;

import com.ysl.commonservice.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class EmlakjetAppException extends RuntimeException {

    private ErrorCode errorCode;
    private Integer httpStatusCode;

    public EmlakjetAppException(ErrorCode code, Integer httpStatusCode) {
        super(code.getMessage());
        this.errorCode = code;
        this.httpStatusCode = httpStatusCode;
    }
}