package hello.hellospring1.repository;

import hello.hellospring1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository {

    //'select m from Member m where m.name =?'과 같은 JPQL 쿼리를 생성
    @Override
    Optional<Member> findByName(String name);
}
