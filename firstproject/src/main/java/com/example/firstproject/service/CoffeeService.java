package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class CoffeeService {
    private CoffeeRepository coffeeRepository;

    public Iterable<Coffee> index() {
        return coffeeRepository.findAll();
    }


    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);}

    public Coffee create(CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        if (coffee.getId() != null) {
            return null;
        }
        return coffeeRepository.save(coffee);

    }

    public Coffee update(Long id, CoffeeDto coffeeDto) {
        // 1. dto -> Entity
        Coffee coffee = coffeeDto.toEntity();
        log.info("id: {}, coffee: {}", id, coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if (target == null || id != coffee.getId()) {
            //400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, coffee: {}", id, coffee.toString());
            return null;
        }

        // 4. 업데이트
        target.patch(coffee);
        return coffeeRepository.save(target);
        }

    public Coffee delete(Long id) {
        // 대상 찾기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 데이터 삭제 및 반환
        coffeeRepository.delete(target);
        return target;
    }

}
