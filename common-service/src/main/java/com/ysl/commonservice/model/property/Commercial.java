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
@Table(name = "commerical")
public class Commercial extends Property {
    private String category;
    private Integer dues;

    @Override
    public PropertyType getPropertyType() {
        return PropertyType.COMMERCIAL;
    }
}