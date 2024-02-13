package com.szs.restapi.globals.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Order(0)
@RequiredArgsConstructor
@Component("jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
              @NonNull HttpServletRequest request
            , @NonNull HttpServletResponse response
            , @NonNull FilterChain filterChain) throws ServletException, IOException {



    }

}
