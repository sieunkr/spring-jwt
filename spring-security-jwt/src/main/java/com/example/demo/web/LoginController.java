package com.example.demo.web;

import com.example.demo.core.CommonResponse;
import com.example.demo.core.MemberDTO;
import com.example.demo.exception.LoginFailedException;
import com.example.demo.exception.TokenValidFailedException;
import com.example.demo.provider.JwtAuthTokenProvider;
import com.example.demo.provider.LoginService;
import com.example.demo.web.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final static long LOGIN_RETENTION_MINUTES = 30;

    @PostMapping
    public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Optional<MemberDTO> optionalMemberDTO = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if (optionalMemberDTO.isPresent()) {

            Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(LOGIN_RETENTION_MINUTES).atZone(ZoneId.systemDefault()).toInstant());
            Optional<String> optionalAuthToken = jwtAuthTokenProvider.createToken(optionalMemberDTO.get().getEmail(), optionalMemberDTO.get().getRole().getCode(), expiredDate);

            if (optionalAuthToken.isPresent()) {
                return CommonResponse.builder()
                        .code("LOGIN_SUCCESS")
                        .status(200)
                        .message(optionalAuthToken.get())
                        .build();
            } else {
                throw new TokenValidFailedException();
            }
        } else {
            throw new LoginFailedException();
        }
    }
}