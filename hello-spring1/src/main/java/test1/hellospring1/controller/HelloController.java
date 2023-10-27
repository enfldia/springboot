package test1.hellospring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
        @GetMapping("/test1")
        public String hello(Model model){
            model.addAttribute("data","집에 가고싶다아아아아");
            return "test";
        }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name",required = false) String name, Model model){
        //-->@RequstParam("name") 이라는 명령어로 받아드린 값을 String name에 담는다.
        model.addAttribute("name",name);    //-->name 에 담긴 값을 attributeName"name"으로 전달
        return "hello-template";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
