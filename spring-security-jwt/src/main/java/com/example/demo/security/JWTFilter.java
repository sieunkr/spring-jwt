package com.example.demo.security;

import com.example.demo.provider.security.JwtAuthToken;
import com.example.demo.provider.security.JwtAuthTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JWTFilter extends GenericFilterBean {

   private static final String AUTHORIZATION_HEADER = "x-auth-token";

   private JwtAuthTokenProvider jwtAuthTokenProvider;

   JWTFilter(JwtAuthTokenProvider jwtAuthTokenProvider) {
      this.jwtAuthTokenProvider = jwtAuthTokenProvider;
   }

   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {

      HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
      Optional<String> token = resolveToken(httpServletRequest);

      if (token.isPresent()) {
         JwtAuthToken jwtAuthToken = jwtAuthTokenProvider.convertAuthToken(token.get());

         if(jwtAuthToken.validate()) {
            Authentication authentication = jwtAuthTokenProvider.getAuthentication(jwtAuthToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      }

      filterChain.doFilter(servletRequest, servletResponse);
   }

   private Optional<String> resolveToken(HttpServletRequest request) {
      String authToken = request.getHeader(AUTHORIZATION_HEADER);
      if (StringUtils.hasText(authToken)) {
         return Optional.of(authToken);
      } else {
         return Optional.empty();
      }
   }
}
