package shopex.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import shopex.dto.MemberFormDto;
import shopex.entity.Member;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링부트 테스트로 전체
@Transactional
class MemberServiceTest {

    @Autowired //필드로 의존성 주입
    MemberService memberService;

    @Autowired //필드로 의존성 주입
    PasswordEncoder passwordEncoder;


    //회원 가입 테스트를 위해 맴버 엔티티 생성 메소드 만듬
    public Member createMember(){
        //데이터를 담을 맴버폼디티오 생성
        MemberFormDto memberFormDto =new MemberFormDto();
        //각각의 데이터 주입
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구");
        memberFormDto.setPassword("1234");
        //Member 엔티티에서 만들어 놓은 createMember를 실행하고 그 값은
        return Member.createMember(memberFormDto,passwordEncoder);
    }

    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest(){
        //member에 createMember를 담고
        Member member = createMember();
        //그 값을 memberServioce의 saveMember 메소드로 저장을 하고. 그 값을 또 savedMember에 담는다.
        Member savedMember = memberService.saveMember(member);

        //(x,y);로 두개의 값을 비교
        assertEquals(member.getEmail(),savedMember.getEmail());
        assertEquals(member.getName(),savedMember.getName());
        assertEquals(member.getAddress(),savedMember.getAddress());
        assertEquals(member.getPassword(),savedMember.getPassword());
        assertEquals(member.getRole(),savedMember.getRole());
        System.out.println("Email: "+ savedMember.getEmail());
        System.out.println("Email: " + member.getEmail());
        System.out.println("password: " + savedMember.getPassword());
        System.out.println("password: " + member.getPassword());

    }
}