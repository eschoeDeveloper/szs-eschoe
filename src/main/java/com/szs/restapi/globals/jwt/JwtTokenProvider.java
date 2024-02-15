package com.szs.restapi.globals.jwt;

import com.szs.restapi.domain.user.UserDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@PropertySource("classpath:jwt.yml")
@Component("jwtTokenProvider")
public class JwtTokenProvider {

    private final String secretKey;
    private final long expirationHours;
    private final String issuer;

    private final Key secretHmacKey;

    public JwtTokenProvider(
      @Value("${secret-key}") String secretKey,
      @Value("${expiration-hours}") long expirationHours,
      @Value("${issuer}") String issuer
    ) {
        this.secretKey = secretKey;
        this.expirationHours = expirationHours;
        this.issuer = issuer;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretHmacKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * JWT Token 생성
     *
     * @param userDTO the user information
     * @return the string
     */
    public String generateJwtToken( UserDTO userDTO ) {

        ZonedDateTime now = ZonedDateTime.now();

        Claims claims = Jwts.claims()
                .issuer(issuer)
                .add("userId", userDTO.getUserId())
                .add("name", userDTO.getName())
                .build();

        String jwtToken = Jwts.builder()
                .claims(claims)
                .issuedAt(Date.from(now.toInstant()))
                .expiration(Date.from(now.plusHours(expirationHours).toInstant()))
                .signWith(secretHmacKey)
                .compact();

        return jwtToken;

    }

    public String getUserId(String jwtToken) {
        return parseClaims(jwtToken).get("userId", String.class);
    }

    public boolean validateJwtToken( String jwtToken ) {

        if( !StringUtils.hasLength(jwtToken) || !StringUtils.hasText(jwtToken) )
            return false;

        try {
            Claims parseClaims = parseClaims(jwtToken);
            return true;
        } catch ( SecurityException | MalformedJwtException exception ) {
            throw new RuntimeException("JWT Token 오류 :: "+ exception.getMessage());
        } catch( ExpiredJwtException exception ) {
            throw new RuntimeException("JWT Token 오류 :: "+ exception.getMessage());
        } catch( UnsupportedJwtException exception ) {
            throw new RuntimeException("JWT Token 오류 :: "+ exception.getMessage());
        } catch( IllegalArgumentException exception ) {
            throw new RuntimeException("JWT Token 오류 :: "+ exception.getMessage());
        }

    }

    public Claims parseClaims( String jwtToken ) {

        try {
            return Jwts.parser().setSigningKey(secretHmacKey).build().parseSignedClaims(jwtToken).getPayload();
        } catch(ExpiredJwtException exception) {
            return exception.getClaims();
        }

    }

}
