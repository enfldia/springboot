package com.example.firstproject.api;

import com.example.firstproject.DTO.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CoffeeApiController {

    //의존성 부여
    @Autowired
    private CoffeeService coffeeService;

    //전체 조회
    @GetMapping("/api/coffees")
    public Iterable<Coffee> index(){
        return coffeeService.index();
    }


    //단건 조회

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
       Coffee coffee = coffeeService.show(id);
       return (coffee != null) ? ResponseEntity.status(HttpStatus.OK).body(coffee) :
                                 ResponseEntity.status(HttpStatus.OK).body(null);
    }
    /*
    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);
        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    */

    //생성

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto){
       Coffee coffee = coffeeDto.toEntity(); //toEntity 상태로 객체를 만들다 보니 Dto에 빈 생성자가 있어야한다(Noargs)
       if(coffee.getId() != null){
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(null);
       }
       Coffee created = coffeeService.save(coffee);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(created);
    }
    /*
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto) {
        Coffee created = coffeeService.create(coffeeDto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    */

    //수정

    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,
                                         @RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeDto.toEntity();
        log.info("id:{},coffee:{}",id,coffee.toString());

        Coffee target = coffeeService
                .show(id);

        if(target == null || id != coffee.getId()){
            log.info("잘못된 요청! id:{}, coffee:{}",id,coffee.toString());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);

        }
        target.patch(coffee);
        Coffee updated = coffeeService.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    /*
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id,
                                         @RequestBody CoffeeDto coffeeDto) {
        Coffee updated = coffeeService.update(id, coffeeDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    */





    //삭제
    
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeService.show(id);

        if(target==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeService.delete(target);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    /*
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }*/
}
