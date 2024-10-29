package com.virginia.utils;

import com.virginia.constants.Constants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {
    // Generate jwt key
    static String keyStr = Constants.SECRET_KEY;
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(keyStr.getBytes());

    // Generate token
    public static String generateToken(Map<String, Object> payload) {
        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }

    // parse token
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {
            throw new BadCredentialsException("JWT invalid");
        }
    }

    // Verify token
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtain claim from token based on key
    public static Object getClaimFromToken(String token, String claimKey) {
        Claims claims = parseToken(token);
        return claims.get(claimKey);
    }
}
