package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String secret = "your_secure_secret_key_32_chars_long";

    // Fixes: "method generateToken in class JwtUtil cannot be applied to given types"
    public String generateToken(String email, String role) {
        return generateToken(email, role, 0L);
    }

    public String generateToken(String email, String role, long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);
        return Jwts.builder().setClaims(claims).setSubject(email)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 36000000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    // Fixes: "cannot find symbol: method extractRole(String)"
    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    // Fixes: "no suitable method found for thenReturn(long)" (Mockito mismatch)
    public String extractUserId(String token) {
        Object userId = extractAllClaims(token).get("userId");
        return userId != null ? String.valueOf(userId) : null;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}