package com.shop.dto;

import com.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) { // of 변환 메서드에서 사용
        return modelMapper.map(itemImg, ItemImgDto.class);
    } // map 소스 메소드 ItmeImg 소스 객체를 대상클래스 (ItemImgDto)를 인자로 받아서 변환 수행
    // static 메소드로 선언해서 ItemImgDto를 생성하지 않아도 호출할 수 있도록 한다.

    // ModelMapper를 사용하여 ItemImg 엔티티를 ItemImgDto로 변환하는 정적 메서드

    /*
    public ItemImgDto() {
        // 기본 생성자
    }

    public ItemImgDto(ItemImg itemImg) {
        this.id = itemImg.getId();
        this.imgName = itemImg.getImgName();
        this.oriImgName = itemImg.getOriImgName();
        this.imgUrl = itemImg.getImgUrl();
        this.repImgYn = itemImg.getRepimgYn();
    }
     */

}
