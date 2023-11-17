package com.shop.service;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정도");
        memberFormDto.setPassword("1234");
        return  Member.createMember(memberFormDto,passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
     void saveMemberTest(){
        Member member = createMember();
        Member savedmember = memberService.saveMember(member);

        assertEquals(member.getEmail(), savedmember.getEmail());
        assertEquals(member.getName(), savedmember.getName());
        assertEquals(member.getAddress(), savedmember.getAddress());
        assertEquals(member.getPassword(), savedmember.getPassword());
        assertEquals(member.getRole(),savedmember.getRole());

        System.out.println("Email:" + savedmember.getEmail());
        System.out.println("Email:" + member.getEmail());
        System.out.println("Email:" + savedmember.getPassword());
        System.out.println("Email:" + member.getPassword());
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
     void saveDupliateMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);});

        assertEquals("이미 가입된 회원입니다.",e.getMessage());
    }
}