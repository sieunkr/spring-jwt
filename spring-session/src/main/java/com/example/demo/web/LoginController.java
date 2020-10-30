package com.example.demo.web;

import com.example.demo.core.MemberDTO;
import com.example.demo.exception.CustomAuthenticationException;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.provider.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/login/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String login(HttpSession httpSession) {


        Optional<MemberDTO> optionalMember = loginService.login("id", "password");

        if(optionalMember.isPresent()) {
            httpSession.setAttribute("role", optionalMember.get().getRole().name());
            httpSession.setMaxInactiveInterval(1800);
        } else {
            throw new LoginFailedException();
        }

        return "ok";
    }
}