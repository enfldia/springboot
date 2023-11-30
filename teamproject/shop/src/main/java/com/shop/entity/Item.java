package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item {

    @Id //PK키 테이블에서 식별자로 사용할 유일키
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품 코드

    @Column(nullable = false,length = 50) //@NouNull 처럼 빈값 불가능.최대 50자 까지
    private String itemNm; //상품명

    @Column(name = "price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고 수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명


    private ItemSellStatus itemSellStatus; //상품 판매 상태 . enum으로 만든 클래스에 셋팅된 값을 받을 수 있다.

    private LocalDateTime regTime; //등록 시간

    private LocalDateTime updateTime; //수정 시간
}
