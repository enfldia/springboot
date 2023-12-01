package jpabook.jpashop.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;


//    public static Member createMember(MemberForm form) {
//        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
//
//        Member member = new Member();
//        member.setName(form.getName());
//        member.setAddress(address);
//        return member;
//    }

}
// @NotEmpty - null이 안 되고 "" 빈 문자열 안 됨. 공백 문자도 안 됨.