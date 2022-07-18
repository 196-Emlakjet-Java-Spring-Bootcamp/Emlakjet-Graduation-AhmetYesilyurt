package com.ysl.commonservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysl.commonservice.enums.AdvertStatus;
import com.ysl.commonservice.model.property.Property;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "adverts")
public class Advert extends BaseEntity{

    private String advertUUID;
    private String title;
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "posted_by_user_id", referencedColumnName = "id")
    @JsonIgnore
    private EmlakUser postedByEmlakUser;


    private BigDecimal cost;
    private Integer duration;

    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus = AdvertStatus.IN_REVIEW;
    private String phoneNumber;
    private Integer view=0;

    @PrePersist
    public void updateInternalFields() {
        this.advertUUID = StringUtils.isEmpty(this.advertUUID) ? UUID.randomUUID().toString() : this.advertUUID;
    }


}
