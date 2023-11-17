package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        return "item/itemForm";
    }

}
