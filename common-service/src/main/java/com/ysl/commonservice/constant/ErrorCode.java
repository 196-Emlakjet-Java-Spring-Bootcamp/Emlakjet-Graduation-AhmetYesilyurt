package com.ysl.commonservice.constant;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_PROPERTY_TYPE("Failed to create a property from request!", 98),
    USER_NOT_FOUND("No person with given id %s found", 97),
    PROPERTY_NOT_FOUND("No property with id %s found!", 96),
    ADVERT_NOT_FOUND("No advert found for the given advert uuid %s", 95),
    AUTHENTICATION_FAILED("Authentication failed", 91),
    PASSWORD_EMPTY("Password is empty", 90),
    SECRET_KEY_NOT_CONFIGURED("Please set up an environment variable 'SECRET_KEY'", 89),
    ADDRESS_NOT_FOUND("No address with given id %s found", 88);

    private String message;
    private Integer code;

    ErrorCode(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public ErrorCode formatted(Object param) {
        this.message = String.format(this.message, param);
        return this;
    }
}