package com.example.demo.core.service;

import com.example.demo.core.service.dto.CoffeeDTO;

import java.util.List;
import java.util.Optional;

public interface CoffeeUseCase {
    Optional<List<CoffeeDTO>> findAll();
}
