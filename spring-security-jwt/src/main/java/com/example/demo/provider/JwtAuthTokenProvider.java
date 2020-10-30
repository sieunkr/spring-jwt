package com.example.demo.provider;

import com.example.demo.core.AuthTokenProvider;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtAuthTokenProvider implements AuthTokenProvider<Claims> {

    private final Key key;

    public JwtAuthTokenProvider(String base64Secret) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean validateToken(String token) {
        return getData(token).isPresent();
    }

    @Override
    public Optional<String> createToken(String id, String role, Date expiredDate) {
        var token = Jwts.builder()
                .setSubject(id)
                .claim("role", role)
                .claim("test", "test")
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate)
                .compact();

        return Optional.ofNullable(token);
    }

    @Override
    public Optional<Claims> getData(String token) {
        try {
            return Optional.of(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody());
        } catch (ExpiredJwtException e) {
            log.info("The authentication token has expired.: {}", e);
            //throw
        } catch (UnsupportedJwtException e) {
            log.info("This is an authentication token that is not supported.: {}", e);
            //throw
        }
        return Optional.empty();
    }
}