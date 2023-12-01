package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
    private String SearchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery="";

    // x. 현재 시간과 상품 등록일을 비교해서 상품 데이터 조회
    // all 상품등록일 전체
    // 1d 최근 하루
    // 1w 최근 일주일
    // 1m 최근 한 달 6m
    // 2. 상품 판매 상태
    // 3. 상품을 조회할 때 어떤 유형으로 조회할지 선텍합니다.
    // itemNm: 상품명 createBy: 상품 등록
    // 4. searchQuery 조회할 검색어 저장 변수
    // searchBy-itemNm 상품명
    // searchBy - createBy 상품 등록자 아이디 기준
}
