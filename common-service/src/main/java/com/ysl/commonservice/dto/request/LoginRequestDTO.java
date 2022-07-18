package com.ysl.commonservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginRequestDTO {
    private String email;
    private String passwordHash;
}