package com.example.demo.provider;

import com.example.demo.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;

    public boolean login(String id, String password) {

        //TODO: 로그인 연동

        //jwtProvider.createToken(id, Arrays.asList("ADMIN", "A"), new Date());
        return true;
    }
}
