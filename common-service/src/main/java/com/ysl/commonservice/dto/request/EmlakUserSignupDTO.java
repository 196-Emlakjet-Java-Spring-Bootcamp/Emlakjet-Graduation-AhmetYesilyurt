package com.ysl.commonservice.dto.request;

import com.ysl.commonservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class EmlakUserSignupDTO {
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String email;
    private String mobileNo;
    private String roleType = Role.INDIVIDUAL.toString();



}