package com.ysl.commonservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "report")
public class Report extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Advert.class,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "advert_id", referencedColumnName = "id")
    @JsonIgnore
    private Advert advertReport;
    private String message;


}
