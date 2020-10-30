package com.example.demo.core;

import java.util.Optional;

public interface LoginUseCase {
    Optional<MemberDTO> login(String id, String password);
}
