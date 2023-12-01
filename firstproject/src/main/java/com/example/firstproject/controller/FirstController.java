package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi") // http://localhost:8080/hi<- 여기 들어갈 문자
    public String nicetoMeetYou(Model model) {
        model.addAttribute("username", "수현");
            // "수현"이라는 값이 "username(html)"이라는 변수에 전달
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYou(Model model) {
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }

    @GetMapping("trigger")
    public String trigger() {
        return "trigger";
    }


}
