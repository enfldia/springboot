package shopex.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import shopex.Service.MemberService;
import shopex.dto.MemberFormDto;
import shopex.entity.Member;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor //생성자 형태의 의존성 주입(DI)
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/new") // /members/new ulr이 들어오면
    public String MemberForm(Model model){
        //view 단에 ${memberFormDto} 에 새로 만든 빈 MemberDto 객체를 보낸다.
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "/member/memberForm";
    }

    @PostMapping("/new") // /members/new ulr이 post값으로 들오면
    //memberFormDto 형태의 값을 받아온다.
    public String MemberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model){

        //Valid로 유효성 검사를 한 memberFormDto에 바인딩 된 에러가 있다면 아래 주소로 이동
        if(bindingResult.hasErrors()){
            return "/member/memberForm";
        }

        try {
            //받아온 memberFormDto와 DI를 통한 passwordEncoder를 인자로 받는 Member의 createMember 메소드로 dto 를 entity로 변환.
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            //memberService의 saveMember 메소드로 member를 인자값으로 실행한다.
            memberService.saveMember(member);
            //객체 상태가 메소드를 실행하기에 적합하지 않다(valid에서 걸리는 경우) 예외가 발생하면
        } catch(IllegalStateException e){
            //해당 오류의 메세지를 추출하여 "errorMessage"란 변수에 모델에 값을 보낸다.
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

}
