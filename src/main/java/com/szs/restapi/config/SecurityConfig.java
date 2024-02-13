package com.szs.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 허용 URL
    private final String[] allowedUrl = {"/swagger-ui/**", "/szs/signup", "/szs/login"};

    @Bean("securityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        requests -> {
                            // 회원가입, 로그인, 스웨거 페이지를 제외한 모든 요청에 대한 인증 받기
                            requests.requestMatchers(allowedUrl).permitAll()
                                    .anyRequest().authenticated();
                        }
                )
                .sessionManagement(sessionManagement -> {
                    // 쿠키 및 세션 사용 안함
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .build();

    }

    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
