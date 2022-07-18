package com.ysl.commonservice.dto.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class ReportRequestDTO {

    private String reportMessage;
    private Long advertId;
}
