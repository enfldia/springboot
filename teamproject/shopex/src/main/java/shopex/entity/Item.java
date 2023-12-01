package shopex.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shopex.constant.ItemSellStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //상품코드

    @Column(nullable = false,length = 50)
    private String itemNm; //상품명
    //null 허용하지 않고 -itemNm 필드는 항상 값을 가져야한다.
    //최대 50자

    @Column(name = "price",nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    private ItemSellStatus itemSellStatus;

    private LocalDateTime regTime; // 등록 시간

    private LocalDateTime updateTime; //수정 시간
}
