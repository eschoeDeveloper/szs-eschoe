package com.szs.restapi.domain.user.whitelist;

import com.szs.restapi.globals.component.CryptoFieldConverter;
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
    @Convert(converter = CryptoFieldConverter.class)
    private String regNo;

}
