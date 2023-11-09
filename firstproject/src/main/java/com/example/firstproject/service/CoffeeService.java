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

    //전체 가져오기
    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }
//    public Iterable<Coffee> index() {
//        return coffeeRepository.findAll();
//    }


    //단건 조회
    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);

    }
    //    public Coffee show(Long id) {
//        return coffeeRepository.findById(id).orElse(null);
//    }

    //생성 및 수정
    public Coffee save(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    //생성

    //    public Coffee create(CoffeeDto coffeeDto) {
//        Coffee coffee = coffeeDto.toEntity();
//        if (coffee.getId() != null) {
//            return null;
//        }
//        return coffeeRepository.save(coffee);
//    }


    //수정

    //    public Coffee update(Long id, CoffeeDto coffeeDto) {
//        Coffee coffee = coffeeDto.toEntity();
//        log.info("id: {}, coffee: {}", id, coffee.toString());
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//        if (target == null || id != coffee.getId()) {
//            log.info("잘못된 요청! id: {}, coffee: {}", id, coffee.toString());
//            return null;
//        }
//        target.patch(coffee);
//        return coffeeRepository.save(target);
//    }




    //삭제

    public void delete(Coffee target) {
        coffeeRepository.delete(target);
    }

//    public Coffee delete(Long id) {
//        Coffee target = coffeeRepository.findById(id).orElse(null);
//        if (target == null) {
//            return null;
//        }
//        coffeeRepository.delete(target);
//        return target;
//    }








}