package com.example.Gateway.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtFilter {

    private static final String secretKey = "5367566859703373367639792F423F452848284D6251655468576HJ5A71347437";

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public void validatetoken(String token) {
        Jwts.parser().
                setSigningKey(getSignKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token)
    {
        return extractAllClaims(token).getSubject();
    }
    public Set<String> extractRoles(String token)
    {
        Claims claims=extractAllClaims(token);
        List<String> roles=claims.get("roles",List.class);
        if(roles!=null)
        {
            return new HashSet<>(roles);
        }
        return null;
    }
}