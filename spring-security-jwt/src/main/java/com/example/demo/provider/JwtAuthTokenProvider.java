package com.example.demo.provider;

import com.example.demo.core.AuthTokenProvider;
import com.example.demo.exception.TokenValidFailedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class JwtAuthTokenProvider implements AuthTokenProvider<Claims> {

    private final Key key;
    private static final String AUTHORITIES_KEY = "role";

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

    //TODO: 메서드 위치 변경
    public Authentication getAuthentication(String token) {

        if(getData(token).isPresent()) {
            Claims claims = getData(token).get();

            Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(new String[]{claims.get(AUTHORITIES_KEY).toString()})
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            User principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, token, authorities);

        } else {
            throw new TokenValidFailedException();
        }
    }
}