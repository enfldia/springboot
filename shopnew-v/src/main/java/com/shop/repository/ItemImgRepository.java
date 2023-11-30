package com.shop.repository;

import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
    ItemImg findByItemIdAndRepImgYn(Long itemId, String repImgYn);
    //findy 뒤에 조건을 붙이면 , 이를 해석하여 데이터 베이스 조회 조건 자동 생성
}
