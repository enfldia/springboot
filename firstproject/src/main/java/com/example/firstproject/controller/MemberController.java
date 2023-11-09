package com.example.firstproject.controller;

import com.example.firstproject.DTO.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/signup")
    public String singUppage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm){
        System.out.println(memberForm.toString()); // form에 적은 값 그대로 나옴
        //log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        System.out.println(member.toString());  //form 데이터를 Entity 형식으로 변경되고 id 필드가 생김
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());   // Entity를 거쳐 레포지토리에 저장되며 @GeneratedValue가 아이디 값을 할당하면서 값을 가짐.
        return "redirect:/members/"+saved.getId();
    }
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",member);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        Iterable<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public  String edit(@PathVariable Long id,Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member",memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm memberForm){
        Member memberEntity = memberForm.toEntity();
        Member updated = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(updated != null){
            memberRepository.save(memberEntity);
        }

        return "redirect:/members/"+ memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id,
                         RedirectAttributes rttr, //rttr 객체에 리다이렉트를 할때 속성값을 전달한다.
                         Model model){
        log.info("삭제 요청이 들어왔습니다");
        //1. 삭제 대상을 가져옴
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 삭제
        if(target != null){
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제되었습니다.");
        }
        // 결과 페이지로 리다이렉트
        return "redirect:/members";
    }
}
