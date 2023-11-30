package com.shop.controller;

import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import com.shop.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberRepository memberRepository;
    private final ItemService itemService;
//    @GetMapping("/")
//    public String holloId(Model model, Principal principal) {
//        if (principal != null) {
//            Member member = memberRepository.findByEmail(principal.getName());
//            model.addAttribute("message", member.getName() + "님, 안녕하세요! "  );
//        }
//
//        return "main";
//    }

    @GetMapping("/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page,
                       Model model){
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() :0, 6);
        Page<MainItemDto> items = itemService.getMainItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage",5);

        return "main";
    }
}
