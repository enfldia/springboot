package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item  extends BaseEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품 코드

    @Column(nullable = false, length = 50)
    // null 허용하지 않고 - itemNm 필드는 항상 값을 가져야 한다.
    // 최대 50자
    private String itemNm; // 상품명

    @Column(name = "price", nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int stockNumber; // 재고수량

    @Lob // 대용량의 데이터 저장
    @Column(nullable = false)
    private String itemDetail; // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; // 상품 판매 상타


    public void updateItem(ItemFormDto itemFormDto) {
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber) {
        int restStock = this.stockNumber - stockNumber;
        if(restStock < 0) {
            throw new OutOfStockException("상폼의 재고가 부족합니다." + " (현재 재고 수량: " + this.stockNumber + ")");
        }
        // 주문 후 남는 재고 수량을 상품의 현재 재고 값으로 할당합니다.
        this.stockNumber = restStock;
    }


    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    } // 주문 취소시 주문했던 수량을 재고로 되돌려줌

}
