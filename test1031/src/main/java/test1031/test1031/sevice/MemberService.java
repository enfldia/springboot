package test1031.test1031.sevice;

import org.springframework.stereotype.Service;
import test1031.test1031.dto.MemberDTO;

@Service    //스프링이 관리해주는 객체 == 스프링 빈
public class MemberService {
    private final MemberRepository memberRepository; //먼저 jpa, mysql dependency 추가

    public void save(MemberDTO memberDTO){
        //repository의 save 메소드 호출
        memberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //Repository의 save메소드 호출 ( 조건. entity객체를 넘겨줘야 함)
    }
}
