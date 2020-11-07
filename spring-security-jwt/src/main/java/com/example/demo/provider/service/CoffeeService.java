package com.example.demo.provider.service;

import com.example.demo.core.service.dto.CoffeeDTO;
import com.example.demo.core.service.CoffeeUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService implements CoffeeUseCase {

    @Override
    public Optional<List<CoffeeDTO>> findAll() {
        return Optional.of(
                Arrays.asList(CoffeeDTO.builder().name("latte").price(1200).build(), CoffeeDTO.builder().name("americano").price(900).build())
        );
    }
}
