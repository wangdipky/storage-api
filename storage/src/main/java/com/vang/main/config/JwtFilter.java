package com.vang.main.config;

import com.vang.main.constant.BaseConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: JwtConfig
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        String authHeader = request.getHeader(BaseConstant.HEADER_AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith(BaseConstant.HEADER_AUTHORIZATION_BEARER)) {

            token = authHeader.substring(7);
        }
        if(token != null && SecurityContextHolder.getContext().getAuthentication() == null && jwtConfig.validateToken(token)) {

            Claims claims = jwtConfig.getClaim(token);
            String role = claims.get(BaseConstant.KEY_ROLE, String.class);
            String username = claims.getSubject();
            UserDetails userDetails = new User(username, UUID.randomUUID().toString(), List.of(new SimpleGrantedAuthority(role)));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}