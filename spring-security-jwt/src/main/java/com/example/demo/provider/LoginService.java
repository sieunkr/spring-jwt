package com.example.demo.provider;

import com.example.demo.core.LoginUseCase;
import com.example.demo.core.MemberDTO;
import com.example.demo.core.Role;
import com.example.demo.core.MemberRepository;
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

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Optional<MemberDTO> login(String email, String password) {

        //사용자 비밀번호 체크
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        //패스워드가 일치하지 않는다면 해당 로직 수행하지 않고 throw 발생
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);


        //로그인 성공하면 아래와 같이 스프링 시큐리티에 인증 객체 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberDTO memberDTO = MemberDTO.builder()
                .userName("eddy")
                .email("sieunkr@gmail.com")
                .role(Role.USER)
                .build();

        return Optional.ofNullable(memberDTO);
    }
}