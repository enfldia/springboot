package com.shop.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
@ToString
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)// -> 지연 로딩
    @JoinColumn(name = "member_id")
    private Member member;

    public static Cart createCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
    // 회원 한명 당 1개의 장바구니를 갖으므로 처음 장바구니에 상픔을 담을때
    // 해당 회원의 장바구니르르 생성해줘야한다.
    // Cart클래스에 회원 엔티티를 파라미터로 받아서 장바구니 엔티티 생성



    //매핑을 해주면 장바구니 엔티티를 조회하면서 회원 엔티티 정보도
    //동시에 가져올 수 있다.

    //즉시 로딩
    //cart테이블과 member 테이블을 조인해서 가져오는 쿼리문이 실행
    //cart 엔티티를 조회하면 member entity도 동시에 가져온다.
    //엔티티를 조회할떄 해당 엔티티와 매핑된 엔티티도 한 번에 조회하는 것을
    //즉시 로딩 이라한다.
    //즉시 로딩은 데이터를 조회할 때 연관된 데이터까지 한 번에 불러오는 것이고,
    //지연 로딩은 필요한 시점에 연관된 데이터를 불러오는 것이라고 할 수 있다.
}
