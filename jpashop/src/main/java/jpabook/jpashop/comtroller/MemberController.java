package jpabook.jpashop.comtroller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.web.MemberForm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String crateForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }
    //new MemberForm() 은 MemberForm 클래스의 빈 인스턴스를 생성하고 "memberForm"속성으로 전달합니다.
    //비어있는 MemberForm 객체가 뷰로 전달된다.

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        //@Valid 어노테이션는 검증을 할떄 사용하고 검증 중 오류가 있으면 ,표 뒤에 적은 것으로 넘어간다.
        if(result.hasErrors()){
            return "members/createMemberForm";
        }
//        Address address = new Address(
//                form.getCity(), form.getStreet(), form.getZipcode());
//        Member member = new Member();
//        member.setName(form.getName());
//        member.setAddress(address);

        Member member = MemberForm.createMember(form);

        memberService.join(member);

        return "redirect:/";
    }

    // @Valid 사용해서 MemberForm 객체를 검증합니다. => 여기서는 @NotEmpty로 검증
    // 검증 결과는 BindingResult 객체에 저장 =>
    // result.hasErros()를 사용하여 검증결과 확인하고 =>
    // 회원 가입 폼(members/createMemberForm)으로 다시 이동
    // MemberForm 객체에서 입력 받은 도시(city),도로명(street), 우편번호(zipcode)
    // 정보를 사용하여 Address 객체를 생성
    // Member 객체 생성하여 회원 이름을 MemberForm 객체에서 입력 받은 이름으로 설정
    // 앞서 만들었던 'Address' 객체를 회원의 주소 정보로 설정
    // memberService.join(member)를 통해 회원 객체를 회원 가입


    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
