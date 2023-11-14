package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName; //회원 이름
    private OrderStatus orderStatus; //주문 상태(ORDER,CANCEL)
}
// 검색 조건 파라미터