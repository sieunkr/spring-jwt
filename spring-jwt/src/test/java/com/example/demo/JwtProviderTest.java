package com.example.demo;

import com.example.demo.provider.JwtProvider;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    private static JwtProvider jwtProvider;

    @BeforeAll
    static void setUp() {
        System.out.println("bef");
        jwtProvider = new JwtProvider("youcantrevealthesecretkey1234012300040");
    }

    @Test
    void getClaims() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 3);

        var token = jwtProvider.createToken("eddy", Arrays.asList("TEST"), calendar.getTime());

        assertTrue(token.isPresent());

        Claims c  = jwtProvider.getData(token.get()).get();

        c.get("roles");

        System.out.println("dd");
    }

    @Test
    void expired() {
        System.out.println("2");
    }


}