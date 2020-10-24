package com.example.demo.provider;

import com.example.demo.exception.CustomAuthenticationException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.provider.JwtProvider;
import com.example.demo.utils.HttpServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle!!");

        Optional<String> optionalToken = HttpServletUtils.getHeader(request, "X-AUTH-TOKEN");

        if (optionalToken.isPresent() && jwtProvider.validateToken(optionalToken.get())) {
            return true;
        } else {
            throw new CustomAuthenticationException(ErrorCode.AUTHENTICATION_FAILED.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle!!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion!!");
    }

}
