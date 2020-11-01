package com.example.demo.web;

import com.example.demo.core.MemberDTO;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.provider.LoginService;
import com.example.demo.web.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.example.demo.core.SecurityConstants.*;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public String login(HttpSession httpSession, @RequestBody LoginRequestDTO loginRequestDTO) {


        var optionalMember = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if(optionalMember.isPresent()) {
            httpSession.setAttribute(KEY_ROLE, optionalMember.get().getRole().name());
            httpSession.setAttribute("email", optionalMember.get().getEmail());
            httpSession.setMaxInactiveInterval(1800);
        } else {
            throw new LoginFailedException();
        }

        return "ok";
    }
}