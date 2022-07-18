package com.ysl.commonservice.model.property;

import com.ysl.commonservice.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "residential")
public class Residential extends Property {

    private Integer noOfRoom;
    private Integer noOfBath;
    private Integer floor;
    private Integer buildingAge;
    @Override
    public PropertyType getPropertyType() {
        return PropertyType.RESIDENTIAL;
    }
}