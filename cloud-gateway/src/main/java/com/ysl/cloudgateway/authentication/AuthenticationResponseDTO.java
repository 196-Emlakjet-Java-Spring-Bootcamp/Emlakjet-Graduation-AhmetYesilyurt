package com.ysl.cloudgateway.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponseDTO {
    private String message;
    private String token;
    private boolean isSuccessfulAuthentication;
}