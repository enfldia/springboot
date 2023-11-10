package jpabook.jpashop.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloewController {
    @GetMapping("/hello")
    public String hello(Model model){
       model.addAttribute("data","hello");
        return "/hello";
    }
}
