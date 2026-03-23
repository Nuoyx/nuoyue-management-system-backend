package org.nuoyue.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final long EXPIRATION_TIME = 6 * 60 * 60 * 1000; // 6 hours
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    /**
     * Generate a JWT token with the given claims.
     * @param claims A map of claims to include in the token
     * @return A signed JWT token as a String
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }


    /**
     * Validate a JWT token and return its claims if valid.
     * @param token The JWT token to validate
     * @return A map of claims if the token is valid, or null if invalid
     */
    public static Claims validateToken(String token) throws Exception{
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}