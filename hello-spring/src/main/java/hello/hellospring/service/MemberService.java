package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원가입
    public Long join(Member member){
        //같은 이름 중복 회원x
        validateDupulicateMember(member);


        memberRepository.save(member);
        return member.getId();

    }
    private void validateDupulicateMember(Member member){
        Optional<Member> result = memberRepository.findByName(member.getName());
        if(result.isPresent()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

//    private void validateDupulicateMember(Member member){
//        memberRepository.findByName(member.getName())
//                .ifPresent(m ->{
//                thorow new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();

    }

    //회원 조회
    public Optional <Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
