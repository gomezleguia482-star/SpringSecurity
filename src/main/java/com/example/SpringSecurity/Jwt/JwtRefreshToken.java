package com.example.SpringSecurity.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtRefreshToken {
    private final String key = "l2n7plHh47OU5N++W2d/TNuW4G6qfKnUUcDSO7y5/dxTaOGn0zCAjVMX7CNOZCa3ocyk0nCmtNbPLHMYd/g2ag==";

    public String generationAccessToken(String email, String role){
        final long expiration = Duration.ofDays(7).toMillis();

        return Jwts.builder()
                .setSubject(email)
                .claim("ROLE", role)
                .claim("Type", "RefreshToken")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignWithKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Key getSignWithKey(){
        byte[] bytes = key.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }
}
