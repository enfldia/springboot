package com.example.firstproject.service;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);

    }

    public Coffee save(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public void delete(Coffee target) {
        coffeeRepository.delete(target);
    }
}