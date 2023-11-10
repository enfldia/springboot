package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface Member1Repository extends CrudRepository<Member,Long> {

}
