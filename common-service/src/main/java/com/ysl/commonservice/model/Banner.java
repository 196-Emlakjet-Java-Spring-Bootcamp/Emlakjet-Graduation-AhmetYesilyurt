package com.ysl.commonservice.model;

import com.ysl.commonservice.enums.PropertyType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "banners")
public class Banner extends BaseEntity {

    private String advertId;
    private String title;
    private String phoneNumber;

    private String city;
    private String district;

    private Float grossSquareMeter;
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    private BigDecimal cost;

}
