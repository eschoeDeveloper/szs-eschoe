package com.szs.restapi;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtTest {

    private final Logger logger = LoggerFactory.getLogger(JwtTest.class);

    @Test
    @DisplayName("SecretKey 원문으로 hmac 알고리즘과 일치하는 SecretKey 객체 생성")
    void generateJwtSecretKey() {

        String keyPlainText = "szs최의승삼쩜삼채용공고과제용API토큰";

        Base64.Encoder encoder = Base64.getEncoder();
        String keyPlainTextByBase64 = encoder.encodeToString(keyPlainText.getBytes(StandardCharsets.UTF_8));

        SecretKey secretKey = Keys.hmacShaKeyFor(keyPlainTextByBase64.getBytes());

        logger.debug(secretKey.getEncoded().toString());

        assertThat(secretKey).isNotNull();

    }

}
