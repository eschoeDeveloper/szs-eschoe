package com.szs.restapi.domain.user.whitelist;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "SZS_USER_WHITELIST")
@Getter @Setter
@ToString
public class UserWhiteListEntity {

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "regNo")
    private String regNo;

}
