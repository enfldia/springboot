package hello.hellospring1.repository;

import hello.hellospring1.domain.Member;


public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository  {
}
