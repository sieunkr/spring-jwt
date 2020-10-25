package com.example.demo.exception;

public class CustomAuthenticationException extends RuntimeException {

    public CustomAuthenticationException(String msg){
        super(msg);
    }
    public CustomAuthenticationException(Exception ex){
        super(ex);
    }

}
