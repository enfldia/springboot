package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//persist 영구저장 하라는 명령어
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class,id);
        //EntityManager 를 사용하여 Mamber 엔티티 클래스와 id를  이용해서 조회
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m form Member m where m.name = :name", Member.class)
                .setParameter("name",name) //실제 name 값을 바인딩합니다.
                .getResultList();               //메소드를 호출하여 jpql 쿼리를 실행하고 결과를 List<Member> 로 받음
        //
        return result.stream().findAny(); // 하나의 결과를 Optional<Member>로 반환
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    @Override
    public void clearStore() {

    }
}

