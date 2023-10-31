package test1031.test1031.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import test1031.test1031.dto.MemberDTO;

@Controller
public class MemberController {
    //회원 가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")    //name값을 requestparam에 담아온다
    public String save(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);

        return "index";
    }








//    @PostMapping("/member/save")    //name값을 requestparam에 담아온다.
//    public String save(@RequestParam("memberEmail") String memberEmail,
//                       @RequestParam("memberPassword") String memberPassword,
//                       @RequestParam("memberName") String memberName){
//                        //@RequestMapping : save.html의 <input> name 과 일치하게 작성한 후 파라미터로 정보를 받아 옵니다.
//        System.out.println("MemberController.save");
//        System.out.println("memberEmail = " + memberEmail + ", memberPassword = " + memberPassword + ", memberName = " + memberName);
//        return "index";
//    }
}
