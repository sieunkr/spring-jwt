package com.example.demo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/coffee/v1")
@RequiredArgsConstructor
public class CoffeeController {


    @GetMapping
    public String test() {

        System.out.println("coffee");
        return "d";
    }

}
