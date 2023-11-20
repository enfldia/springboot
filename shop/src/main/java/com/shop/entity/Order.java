package com.shop.entity;

import com.shop.constant.OrderSatatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;//주문일

    @Enumerated(EnumType.STRING)
    private OrderSatatus orderSatatus;//주문상태

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,
                orphanRemoval = true)//고아 객체 삭제 = 참
    private List<OrderItem> orderItems = new ArrayList<>();
    //외래키(order_id) 가 order_item 테이블에 있으므로
    //연관관계 주인은 OrderItem
    //OrderItem 에 Order에 의해서 관리된다.


}
