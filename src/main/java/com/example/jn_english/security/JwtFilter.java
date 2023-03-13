package com.example.jn_english.security;


import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseRequest(request);
        if (token != null) {
            Claims claims = tokenProvider.parseToken(token);
            SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(claims.getSubject()));
        }
        filterChain.doFilter(request, response);
    }

    private String parseRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token!= null&& token.startsWith("Bearer")) {
            return token.substring(7);
        }
        return null;
    }
}
