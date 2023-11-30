package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNM(String itemNm, String itemDetail); //아이템 클래스의 리스트 타입으로 저장

    List<Item> findByItemNmOrItemDetail(String itemNm,String itemDetail);
}