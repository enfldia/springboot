package com.example.secondproject.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody 의 기능을 하는 어노테이션
//페이지 이동이 안되므로 요청 받았던 곳으로 return값을 보내준다.
//@RequestBody(요청하는 입장)는 데이터를 Object로 변환
//@ResponseBody(받은 요청을 처리하는 입장)는 Object를 JSON 형태로 변환
@RequiredArgsConstructor
public class SecondController {
    @GetMapping("/api/hello")
    public String hello(){return "hellow world";}
}
