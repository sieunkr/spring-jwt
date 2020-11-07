package com.example.demo.core.service;

import com.example.demo.core.security.AuthToken;
import com.example.demo.core.service.dto.MemberDTO;

import java.util.Optional;

public interface LoginUseCase {
    Optional<MemberDTO> login(String id, String password);
    AuthToken createAuthToken(MemberDTO memberDTO);
}
