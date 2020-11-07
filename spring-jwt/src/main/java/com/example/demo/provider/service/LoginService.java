package com.example.demo.provider.service;

import com.example.demo.core.security.AuthToken;
import com.example.demo.core.service.LoginUseCase;
import com.example.demo.core.service.dto.MemberDTO;
import com.example.demo.core.security.Role;
import com.example.demo.provider.security.JwtAuthToken;
import com.example.demo.provider.security.JwtAuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final static long LOGIN_RETENTION_MINUTES = 30;

    @Override
    public Optional<MemberDTO> login(String email, String password) {

        //TODO: 로그인 연동

        //로그인 성공했다고 가정하고..
        MemberDTO memberDTO = MemberDTO.builder()
                .userName("eddy")
                .email(email)
                .role(Role.USER)
                .build();

        return Optional.ofNullable(memberDTO);
    }

    //TODO: 네이밍
    @Override
    public AuthToken createAuthToken(MemberDTO memberDTO) {

        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
        return jwtAuthTokenProvider.createAuthToken(memberDTO.getEmail(), memberDTO.getRole().getCode(), expiredDate);
    }
}