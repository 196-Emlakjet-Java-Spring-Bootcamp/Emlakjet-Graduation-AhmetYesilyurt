package com.ysl.commonservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerResponseDTO {

    private String title;
    private String advertUUID;
    private String phoneNumber;
    private String city;
    private String district;
    private LocalDateTime postedDate;
    private Float squareMeter;

}
