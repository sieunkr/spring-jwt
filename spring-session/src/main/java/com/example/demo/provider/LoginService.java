package com.example.demo.provider;

import com.example.demo.core.LoginUseCase;
import com.example.demo.core.MemberDTO;
import com.example.demo.core.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

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
}