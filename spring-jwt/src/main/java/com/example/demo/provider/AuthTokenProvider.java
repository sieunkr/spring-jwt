package com.example.demo.provider;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AuthTokenProvider<T> {
    boolean validateToken(String token);
    Optional<String> createToken(String id, List<String> authorities, Date expiredDate);
    Optional<T> getData(String token);
}
