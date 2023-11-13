//package jpabook.jpashop.repository;
//
//import jpabook.jpashop.domain.Member;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//// @RunWith(SpringRunner.class) // JUnit4 전용
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    void save() throws Exception {
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);
//
//        // then
//        //Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        //Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        assertEquals(findMember.toString(),member.toString());
//
//    }
//
//
//    @Test
//    void find() {
//    }
//}