package com.ysl.commonservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdvertUpdateDTO {

    private String title;
    private String description;
    private BigDecimal cost;
    private Integer duration;
    private String phoneNumber;
}
