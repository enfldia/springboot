package com.shop.controller;

import com.shop.dto.FaqDto;
import com.shop.entity.Faq;
import com.shop.entity.Member;
import com.shop.repository.FaqRepository;
import com.shop.repository.MemberRepository;
import com.shop.service.FaqService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FaqController {

    private final MemberRepository memberRepository;

    private final FaqRepository faqRepository;

    private final FaqService faqService;

    @GetMapping("/faq/faq")
    public String faq(Model model) {
        List<Faq> faqs = faqRepository.findAll();
        model.addAttribute("faqs", faqs);
        return "faq/faq";
    }
    @GetMapping(value = "/admin/faq/new")
    public String faqForm(Model model) {
        model.addAttribute("faq", new FaqDto());
        return "faq/faqForm";
    }
    @PostMapping(value = "/admin/faq/new")
    public String faqNew(FaqDto faqDto) {
        faqService.saveFaq(faqDto);
        return "faq/faq";
    }
//    private static final int PAGE_SIZE = 10;
//
//    @GetMapping("/news/faq/faq")
//    public String faqPage(
//            @RequestParam(name = "page", defaultValue = "1") int page,
//            Model model, Principal principal
//    ) {
//        // 유저 로그인
//        if (principal != null) {
//            String user = principal.getName();
//            Member userInfo = memberRepository.findByEmail(user);
//            model.addAttribute("userInfo", userInfo);
//            List<Faq> faq = faqRepository.findByMemberEntityId(userInfo.getId());
//            int reviewNum = faq.size();
//            model.addAttribute("reviewNum",reviewNum);
//        }
//
//        Page<Faq> faqPage = faqRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE));
//        int totalPages = faqPage.getTotalPages();
//
//        List<Integer> pageNumbers = new ArrayList<>();
//        for (int i = 1; i <= totalPages; i++) {
//            pageNumbers.add(i);
//        }
//
//        model.addAttribute("faqs", faqPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("pageNumbers", pageNumbers);
//
//        return "news/faq/faq";
//    }

}
