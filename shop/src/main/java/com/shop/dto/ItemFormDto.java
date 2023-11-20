package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ItemFormDto {
    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotBlank(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotBlank(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();


    public Item createItem(){ //Dto를 엔티티로 변환
        return modelMapper.map(this,Item.class);
    }
    //현재 ItemFormDto 객체를 사용해서 Item 엔티티를 생성
    public static ItemFormDto of(Item item){//엔티티를 Dto로 변환
        return modelMapper.map(item,ItemFormDto.class);
    }
    //주어진 Item 엔티티를 사용하여 ItemFormDto를 생성하는 정적 메서드
    //엔티티의 필드 값을 DTI로 매핑합니다.
}
