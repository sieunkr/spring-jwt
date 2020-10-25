package com.example.demo.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {

    public boolean login(String id, String password) {

        //TODO: 로그인 연동

        return true;
    }
}