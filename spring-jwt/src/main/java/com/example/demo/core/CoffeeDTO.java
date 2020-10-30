package com.example.demo.core;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoffeeDTO {

    private String name;
    private Integer price;

}
