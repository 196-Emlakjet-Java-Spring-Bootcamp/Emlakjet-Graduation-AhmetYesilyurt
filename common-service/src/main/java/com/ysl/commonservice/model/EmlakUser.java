package com.ysl.commonservice.model;

import com.ysl.commonservice.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "emlak_users")
public class EmlakUser extends BaseEntity {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String mobileNo;

    @Enumerated(EnumType.STRING)
    private Role role = Role.INDIVIDUAL;

    @OneToMany(targetEntity = Advert.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "postedByEmlakUser")
    private Set<Advert> publishedAdverts = new LinkedHashSet<>();

    public EmlakUser(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
