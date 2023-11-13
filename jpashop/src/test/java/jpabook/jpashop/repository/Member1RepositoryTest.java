//package jpabook.jpashop.repository;
//
//
//import jpabook.jpashop.domain.Member;
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
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class Member1RepositoryTest {
//    @Autowired
//    Member1Repository member1Repository;
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public  void save() throws Exception {
//        //given
//        Member member = new Member();
//        member.setUsername("memberA");
//        //when
//        Member savedMember = member1Repository.save(member);
//
//        // then
//        Member findMember = member1Repository.findById(savedMember.getId()).orElse(null);
//
//        assertNotNull(findMember);
//        assertEquals(member.getId(), findMember.getId());
//        assertEquals(member.getUsername(), findMember.getUsername());
//        assertEquals(member.toString(), findMember.toString(), "일치하는지 출력.");
//        System.out.println(findMember.toString());
//        System.out.println(member.toString());
//    }
//    @Test
//    void find() {
//    }
//}