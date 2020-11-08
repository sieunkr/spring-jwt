package com.example.demo.exception;

public class CustomAuthenticationException extends RuntimeException {

    public CustomAuthenticationException(){
        super(ErrorCode.INVALID_JWT_TOKEN.getMessage());
    }

    public CustomAuthenticationException(Exception ex){
        super(ex);
    }
}
