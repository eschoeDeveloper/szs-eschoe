package com.szs.restapi.domain.user;


import com.szs.restapi.globals.component.CryptoFieldConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "SZS_USER")
@Getter @Setter
public class UserEntity {

    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "reg_no")
    @Convert(converter = CryptoFieldConverter.class)
    private String regNo;

}
