package hello.hellospring1.service;

import hello.hellospring1.domain.Member;
import hello.hellospring1.repository.MemberRepository;
import hello.hellospring1.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private MemberRepository memberRepository ;

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //회원가입
    public Long join(Member member){
        //같은 이름 중복회원 X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

  ///  private void validateDuplicateMember(Member member) {
  //      Optional<Member> result = memberRepository.findByName(member.getName());
  //      if (result.isPresent()) {
  ///          throw new IllegalStateException("이미 존재하는 회원입니다.");
   //     }
  //  }
  private void validateDuplicateMember(Member member) {
      memberRepository.findByName(member.getName())
              .ifPresent(m->{
                  throw new IllegalStateException("이미 존재하는 회원입니다.");
              });
  }

//전체 회원 조회
public List<Member> findMembers(){
    return memberRepository.findAll();
}

//회원조회
public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
}


}
