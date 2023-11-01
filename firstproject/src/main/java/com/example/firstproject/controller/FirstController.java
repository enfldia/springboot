package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController{

    @GetMapping("/hi")
    public String nicetoMeetyou(Model model){
        model.addAttribute("username","상현");
        return "greetings";
    }

    @GetMapping("/bye")
    public String goodbye(Model model){
        model.addAttribute("nickname","홍길동");
                return "goodbye";
    }
}
