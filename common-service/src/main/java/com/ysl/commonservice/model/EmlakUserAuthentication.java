package com.ysl.commonservice.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "emlak_user_authentication")
public class EmlakUserAuthentication extends BaseEntity {

    @OneToOne(targetEntity = EmlakUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "emlak_user_id", referencedColumnName = "id")
    private EmlakUser emlakUser;
    private String passwordHash;

}
