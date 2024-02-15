package com.szs.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void encode() {

//        String newPwd = "chldmltmd";
        String newPwd = "#@mkd2ek91";
        System.out.println( encoder.encode(newPwd) );

    }

    @Test
    void matches() {

//        String newPwd = "chldmltmd";
//        String orgPwd = "$2a$10$mWl2NTsPpn9CT5dXtn1DT.K1KKaHS7aV8kG2bJqwGe6qgSl9dyDye";

        String newPwd = "#@mkd2ek91";
        String orgPwd = "$2a$10$p4Dxa3jPvflgCjNSrEVLY.0r0Oc8TJ9gnUrdyzEDl.3U/3acD7nnu";

        System.out.println(encoder.matches(newPwd, orgPwd));


    }

}
