package com.example.demo.provider.service;

import com.example.demo.core.security.Role;
import com.example.demo.core.service.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void loginSuccessTest() {

        //given
        var expectedEmail = "sieunkr@gmail.com";
        var expectedRole = Role.USER.getCode();

        //when
        Optional<MemberDTO> m = loginService.login("sieunkr@gmail.com", "password");


        //then
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assertEquals(expectedEmail, ((User)authentication.getPrincipal()).getUsername());
        assertEquals(expectedRole, authentication.getAuthorities().stream().findFirst().get().toString());

    }

    @Test
    void loginFailedTest() {

        //given, when, then
        assertThrows(BadCredentialsException.class, () -> {
            loginService.login("sieunkr@gmail.com", "invalid_password");
        });
    }
}