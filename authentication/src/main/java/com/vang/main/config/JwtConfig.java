package com.vang.main.config;

import com.vang.main.constant.BaseConstant;
import com.vang.main.service.UserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * CreatedDate: 28/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: JwtConfig
 */
@Component
public class JwtConfig {

    @Value("${jwt.secret.key}")
    private String secret;

    private final UserDetailService userDetailService;

    @Autowired
    public JwtConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public String generateToken(String username) {

        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        Map<String, String> claims = new HashMap<>();
        claims.put(BaseConstant.KEY_ROLE, userDetails.getAuthorities().iterator().next().getAuthority());
        return createToken(username, claims);
    }

    public String extractUsername(String token) {

        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        return extractUsername(token).equals(userDetails.getUsername()) && new Date().before(extractClaims(token, Claims::getExpiration));
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

        Claims claims = getClaim(token);
        return claimsResolver.apply(claims);
    }

    private Claims getClaim(String token) {

        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(String username, Map<String, String> claims) {

        return Jwts
                .builder()
                .subject(username)
                .claims(claims)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
    }
}