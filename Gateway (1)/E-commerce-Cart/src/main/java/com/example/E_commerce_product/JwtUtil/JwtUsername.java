package com.example.E_commerce_product.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtUsername {
    public final String key="5367566859703373367639792F423F452848284D6251655468576HJ5A71347437";
    private Key getSignKey()
    {
        byte[] KeyBytes= Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(KeyBytes);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private Claims extractClaims(String token) {
        return Jwts.parser().
                setSigningKey(getSignKey()).
                build().
                parseClaimsJws(token).
                getBody();
    }
}
