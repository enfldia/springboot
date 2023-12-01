package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("Kim");
        // when
        Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
        System.out.println("기대하는 값: " + member);
        System.out.println("실제 나온 값: " + memberRepository.findOne(saveId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given - 주어진 상황
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        // when - 행동
        memberService.join(member1);

        // then - 결과
        // assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 예외를 메시지 검증
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
        System.out.println(thrown.getMessage());

    }

}