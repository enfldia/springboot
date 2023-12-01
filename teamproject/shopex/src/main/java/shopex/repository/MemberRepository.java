package shopex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopex.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
