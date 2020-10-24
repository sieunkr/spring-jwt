package com.example.demo.config;

import com.example.demo.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secret);
    }
}
