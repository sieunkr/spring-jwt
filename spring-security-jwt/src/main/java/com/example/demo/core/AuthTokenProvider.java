package com.example.demo.core;

import java.util.Date;
import java.util.Optional;

public interface AuthTokenProvider<T> {
    boolean validateToken(String token);
    Optional<String> createToken(String id, String role, Date expiredDate);
    Optional<T> getData(String token);
}
