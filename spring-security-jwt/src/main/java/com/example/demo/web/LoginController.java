package com.example.demo.web;

import com.example.demo.core.CommonResponse;
import com.example.demo.core.MemberDTO;
import com.example.demo.core.Role;
import com.example.demo.exception.LoginFailedException;
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
@RequestMapping("/api/login/v1")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @PostMapping
    public CommonResponse login(@RequestBody LoginRequestDTO loginRequestDTO) {

        Optional<MemberDTO> optionalMemberDTO = loginService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(30);
        Date expiredDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Optional<String> optionalAuthToken = jwtAuthTokenProvider.createToken("id", Role.USER.name(), expiredDate);

        if (optionalMemberDTO.isPresent() & optionalAuthToken.isPresent()) {

            return CommonResponse.builder()
                    .code("LOGIN_SUCCESS")
                    .status(200)
                    .message(optionalAuthToken.get())
                    .build();
        } else {
            throw new LoginFailedException();
        }
    }
}
