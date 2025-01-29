package com.vang.main.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    public String extractUsername(String token) {

        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {

        return new Date().before(extractClaims(token, Claims::getExpiration));
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

        Claims claims = getClaim(token);
        return claimsResolver.apply(claims);
    }

    public Claims getClaim(String token) {

        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}