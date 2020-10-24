package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String login(HttpSession httpSession) {

        loginService.login("id", "password", httpSession);

        return "ok";
    }

}
