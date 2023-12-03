package shopex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopex.entity.Member;
import shopex.repository.MemberRepository;

@Service
@Transactional // 스프링프레임 워크로 임포트
@RequiredArgsConstructor // final이거나 notnull인 것들 의존성 주입할 때 사용
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    //맴버 저장
    public Member saveMember(Member member){
        //중복 확인
        validateDuplicateMember(member);
        //아래의 중복 검사가 통과되면 memberRepository의 save를 통해 데이터베이스에 저장
        return memberRepository.save(member);
    }

    //이메일 중복 확인
    //saveMember 에서 받은 member 엔티티를 받아서
    private void validateDuplicateMember(Member member){
        // member.getEmail 로 memberFormDto에서 받은 이메일 값을 뽑아내서
        // memberRepository의 findByEmail() 메소드 데이터베이스에 같은 이메일이 있는지
        // 확인하고 그 값을 findMember에 넣는다.
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null) //빈 값이 아니면
            throw new IllegalStateException("이미 가입된 회원입니다.");
    }

    @Override
    //UserDetails 클래스이고 인자값으로 email을 받는 loadUserByUsername 매소드 생성 예외 발생시 UsernameNotFoundException 에 넘김
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        //email로 맴버 리보지토리에서 해당 데이터가 있는지 찾고 값을 member에 저장
        Member member = memberRepository.findByEmail(email);

        //member값이 비어있단다면 새로운 UsernameNotFoundException 만듬
        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        //맴버의 값이 있다면 빌드 패턴으로 User라는 객체에 member의 name,passowrd,role 값을 넣고 생성한다.
        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();

    }
}
