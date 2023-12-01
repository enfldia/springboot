package shopex.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
import shopex.constant.Role;
import shopex.dto.MemberFormDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    //memberFormDto를 받아서 엔티티인 member 생성을 하는데.
    // password 변환을 위해 SecurityConfig에서 PasswordEncoder를 받아온다.
    public static  Member createMember(MemberFormDto memberFormDto,
                                       PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        //memberFormDto에서 받은 password를 암호화하고 member에 입력
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;
    //생성된 Entity member 는 데이터베이스랑 연결이 되는 MemberService에서 사용한다.
    }
}
