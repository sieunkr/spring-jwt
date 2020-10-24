package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {

    public boolean login(String id, String password, HttpSession httpSession) {

        //TODO: 로그인 연동

        httpSession.setAttribute("role", "member");

        //TODO: Session... 인증 정보 저장..

        return true;
    }
}