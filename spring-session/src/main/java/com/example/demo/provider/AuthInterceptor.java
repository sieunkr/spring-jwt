package com.example.demo.provider;

import com.example.demo.exception.CustomAuthenticationException;
import com.example.demo.exception.ErrorCode;
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


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle!!");

        System.out.println(request.getSession());

        //TODO: 클린코드.
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("member")) {
            return true;
        } else {
            throw new CustomAuthenticationException(ErrorCode.AUTHENTICATION_FAILED.getMessage());
        }
    }
}