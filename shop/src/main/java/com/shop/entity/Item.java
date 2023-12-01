package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;                        //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;                  //상품명
    //null 허용 하지 않고 - itemNm 필드는 항상 값을 가져야 한다. 최대 50자

    @Column(name = "price", nullable = false)
    private int price;                      //가격

    @Column(nullable = false)
    private int stockNumber;                //재고 수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;              //상품 상세 설명
    //@Lob - 대용량의 데이터를 저장

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  //상품 판매 상태

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
            throw new OutOfStockException("상품의 재고가 부족합니다. " + "현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
        //주문 후 남은 재고 수량을 상품의 현재 재고 값으로 할당합니다.
    }

    public void addStock(int stockNumber) {
        this.stockNumber += stockNumber;
    }
    //주문취소시 주문했던 수량만큼 상품재고를 더해준다
//    private LocalDateTime regTime;          //등록 시간

//    private LocalDateTime updateTime;       //수정 시간
}
