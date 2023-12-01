package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    // CrudRepository<사용하려는Dto(entity폴더의 파일명), primaryType>

}
