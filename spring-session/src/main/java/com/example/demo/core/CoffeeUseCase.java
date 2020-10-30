package com.example.demo.core;

import java.util.List;
import java.util.Optional;

public interface CoffeeUseCase {
    Optional<List<CoffeeDTO>> findAll();
}
