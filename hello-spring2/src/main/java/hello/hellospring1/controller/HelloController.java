package hello.hellospring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HelloController {
    @GetMapping("hellohi")
    public String hello(Model model){
        model.addAttribute("data", "허준석");
        return "hello";
    }
    @GetMapping("/fruit")
    public String getFruit(Model model) {
        //Model 객체를 통해 view(타임리프 html )로 전달
        Map<String, String> fruitmap = new HashMap<String, String>();
        fruitmap.put("fruit1", "apple");
        fruitmap.put("fruit2", "banana");
        fruitmap.put("fruit3", "orange");
        model.addAttribute("fruit", fruitmap);

        return "fruit/fruit";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name
        , Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 단순 api 방식 - string 값 가지고 오기

    @GetMapping("hello-string")
    @ResponseBody //http 해더부와 바디부가 있는데 바디부에 이데이터를 직접넣어줌
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
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
