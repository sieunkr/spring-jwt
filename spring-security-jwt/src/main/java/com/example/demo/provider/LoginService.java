package com.example.demo.provider;

import com.example.demo.core.LoginUseCase;
import com.example.demo.core.MemberDTO;
import com.example.demo.core.Role;
import com.example.demo.core.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Optional<MemberDTO> login(String email, String password) {

        //TODO: 로그인 연동

        UsernamePasswordAuthenticationToken authenticationToken =
                //new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //로그인 성공했다고 가정하고..
        MemberDTO memberDTO = MemberDTO.builder()
                .userName("eddy")
                .email("sieunkr@gmail.com")
                .role(Role.USER)
                .build();

        return Optional.ofNullable(memberDTO);
    }
}