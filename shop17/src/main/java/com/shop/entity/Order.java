package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order  extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    // 외래키(order_id)가 order_item 테이블에 있으므로 연관관계 주인은 '다'쪽인 OrderItem.
    // OrderItem의 order에 의해서 관리된다.

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem); // orderItem 객체를 order 객체에 orderItems 추가
        orderItem.setOrder(this); // 양방향 연관관계이므로 orderItem 객체에 order객체를 셋팅
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);

        for(OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
            return totalPrice;
    } // 총주문금액을 구하는 메소드입니다.

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL; // order => CANCEL
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

}
