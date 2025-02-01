package com.example.deliveryapp.global.jwt;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.deliveryapp.domain.user.entity.User;
import com.example.deliveryapp.domain.user.enumerate.UserRole;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        //jwt 유효성 검사를 하지않음
        if ("/v1/users/sign-in".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = resolveToken(request);

        // 토큰이 유효한 경우, 사용자 정보를 요청 속성에 저장
        if (token != null && tokenProvider.validateToken(token)) {
            String email = tokenProvider.getEmail(token);
            String role = tokenProvider.getRole(token);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header에서 토큰 조회 및 Bearer 문자열 제거 후 반환하는 메소드
    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        // Token 정보 존재 여부 및 Bearer 토큰인지 확인
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }
}
