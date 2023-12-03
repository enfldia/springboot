package shopex.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //장바구니 엔티티가 일방적으로 회원 엔티티를 참조하고 있으니 일대일 단방향 매핑을 함
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
