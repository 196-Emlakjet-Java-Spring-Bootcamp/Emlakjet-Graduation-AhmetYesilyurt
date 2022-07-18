package com.ysl.commonservice.model.property;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysl.commonservice.enums.PropertyType;
import com.ysl.commonservice.model.Address;
import com.ysl.commonservice.model.BaseEntity;
import com.ysl.commonservice.model.EmlakUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Property extends BaseEntity {

    private String description;
    private Float grossSquareMeter;
    private Boolean isForSale;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.ALL})
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private EmlakUser owner;

    public abstract PropertyType getPropertyType();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}