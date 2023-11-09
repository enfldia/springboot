package com.example.firstproject.api;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    //Get
    @GetMapping("/api/coffee")
    public Iterable<Coffee> index(){
        return coffeeRepository.findAll();
    }

    @GetMapping("api/coffee/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
       Coffee coffee = coffeeRepository.findById(id).orElse(null);
       return (coffee != null) ? ResponseEntity.status(HttpStatus.OK).body(coffee) :
                                 ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
