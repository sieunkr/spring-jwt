package com.example.demo.provider;

import com.example.demo.exception.CustomAuthenticationException;
import com.example.demo.utils.HttpServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle!!");

        Optional<String> optionalToken = HttpServletUtils.getHeader(request, "x-auth-token");

        if (optionalToken.isPresent() && jwtAuthTokenProvider.validateToken(optionalToken.get())) {
            return true;
        } else {
            throw new CustomAuthenticationException();
        }
    }
}
