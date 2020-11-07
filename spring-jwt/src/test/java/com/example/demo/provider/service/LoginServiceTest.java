package com.example.demo.provider.service;

import com.example.demo.config.JwtConfiguration;
import com.example.demo.core.security.AuthToken;
import com.example.demo.core.security.Role;
import com.example.demo.core.service.dto.MemberDTO;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {LoginService.class, JwtConfiguration.class})
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    void loginTest() {

        //given
        var expectedEmail = "eddy.kim@gamil.com";

        //when
        Optional<MemberDTO> memberDTO = loginService.login(expectedEmail, "password");

        //then
        assertTrue(memberDTO.isPresent());
        assertEquals(expectedEmail, memberDTO.get().getEmail());
    }

    @Test
    void createAuthTokenTest() {


        //given
        var expectedSub = "eddy.kim@gmail.com";
        var expectedRole = Role.USER.getCode();

        MemberDTO memberDTO = MemberDTO.builder()
                .email("eddy.kim@gmail.com")
                .role(Role.USER)
                .userName("eddy")
                .build();

        //when
        AuthToken a = loginService.createAuthToken(memberDTO);


        //then
        assertEquals(expectedSub, ((Claims)a.getData()).get("sub"));
        assertEquals(expectedRole, ((Claims)a.getData()).get("role"));
    }
}