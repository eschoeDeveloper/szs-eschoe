package com.szs.restapi.globals.jwt;

import com.szs.restapi.globals.security.SzsUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component("jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final SzsUserDetailsService szsUserDetailsService;
    @Override
    protected void doFilterInternal(
              @NonNull HttpServletRequest request
            , @NonNull HttpServletResponse response
            , @NonNull FilterChain filterChain) throws ServletException, IOException {

        if(!request.getRequestURI().contains("/szs/login") && !request.getRequestURI().contains("/szs/signup") && !request.getRequestURI().contains("/h2-console") && !request.getRequestURI().contains("/swagger-ui")) {

            String authorization = request.getHeader("Authorization");

            if (!StringUtils.hasLength(authorization) || !StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
                throw new RuntimeException("Authorization 이 존재하지 않거나 올바르지 않은 값입니다.");
            }

            String jwtToken = authorization.replaceAll("Bearer", "").trim();

            jwtTokenProvider.validateJwtToken(jwtToken);

            String userId = jwtTokenProvider.getUserId(jwtToken);

            UserDetails userDetails = szsUserDetailsService.loadUserByUsername(userId);

            if (ObjectUtils.isEmpty(userDetails)) {
                throw new RuntimeException("유저 상세정보(userDetails)가 존재하지 않습니다.");
            }

            // UserDetailService 검증
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());

            // 접근권한 지정
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }

        filterChain.doFilter(request, response); // 다음 필터로 전환


    }

}
