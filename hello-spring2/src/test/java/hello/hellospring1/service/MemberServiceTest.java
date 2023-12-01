package hello.hellospring1.service;

import hello.hellospring1.domain.Member;
import hello.hellospring1.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //give 상황이 주어져서
        Member member = new Member();
        member.setName("Spring");
        //when 실행했을때
        Long saveId = memberService.join(member);
        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
        System.out.println("멤버이름 :" + member.getName());
        System.out.println("파인드멤버이름 :" + findMember.getName());
    }

    @Test
   public void 중복저장테스트() {
       //give 상황이 주어져서
        Member member1 = new Member();
        member1.setName("Spring");

        //give 상황이 주어져서
        Member member2 = new Member();
        member2.setName("Spring");

        memberService.join(member1);
      //  try{
       //     memberService.join(member2);
      //      fail("예외가 발생해야 한다");
      //  }catch(IllegalStateException e){
      //      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
      //  }
        //로직 실행시 예외가 터져야 한다.
       IllegalStateException e =
        assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        System.out.println(e.getMessage());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}