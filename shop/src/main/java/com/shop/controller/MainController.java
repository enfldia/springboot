package com.shop.controller;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberRepository memberRepository;
    @GetMapping("/")
    public String holloId(Model model, Principal principal) {
        if (principal != null) {
            Member member = memberRepository.findByEmail(principal.getName());
            model.addAttribute("message", member.getName() + "님, 안녕하세요! "  );
        }

        return "main";
    }
}
