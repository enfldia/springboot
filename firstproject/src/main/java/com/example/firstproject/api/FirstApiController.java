package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;


public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world!";
    }
    //


}
