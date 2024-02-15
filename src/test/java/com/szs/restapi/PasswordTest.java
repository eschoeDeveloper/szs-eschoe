package com.szs.restapi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordTest {

    private Logger logger = LoggerFactory.getLogger(PasswordTest.class);

    private PasswordEncoder encoder;

    @BeforeEach
    public void beforeEach() {
        this.encoder = new BCryptPasswordEncoder();
    }

    /**
     * 사용자 비밀번호 암호화
     */
    @Test
    void encode() {

        String newPwd = "chldmltmd";
        String encodeText = encoder.encode(newPwd);

        Assertions.assertThat(encodeText).isNotNull();
        
        logger.debug("암호화 :: " + encodeText);

    }

    /**
     * 사용자 비밀번호 매칭
     */
    @Test
    void matches() {

        String newPwd = "chldmltmd";
        String orgPwd = "$2a$10$mWl2NTsPpn9CT5dXtn1DT.K1KKaHS7aV8kG2bJqwGe6qgSl9dyDye";

        logger.debug("암호 비교 :: " + encoder.matches(newPwd, orgPwd));
        
    }

}
