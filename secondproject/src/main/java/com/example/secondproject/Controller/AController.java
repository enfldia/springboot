package com.example.secondproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AController {
    @GetMapping("/")
    public String homeView(Model model){
        model.addAttribute("username","상현");
        return "greetings";
    }
    @GetMapping("/bye")
    public String goodbye(Model model){
        model.addAttribute("username","상현");
        return "goodbye";
    }
}
