package com.ysl.commonservice.dto.request;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AdvertRequestDTO extends BaseRequestDTO{

    private String title;
    private String description;
    private Long propertyId;
    private BigDecimal cost;
    private Integer duration;
    private String phoneNumber;
}
