package com.ysl.commonservice.dto.request;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AddressRequestDTO extends BaseRequestDTO{
    private String city;
    private String district;
    private String addressDesc;
}
