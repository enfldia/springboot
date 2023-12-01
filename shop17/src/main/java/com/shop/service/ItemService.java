package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.dto.ItemImgDto;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;


    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        // 상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        // 이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if (i == 0)
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            // DB 저장시 공백 제거
            if (!StringUtils.isEmpty(itemImgFileList.get(i).getOriginalFilename())) {
                itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            }
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId) {
        // 해당 상품에 연결된 이미지를 id
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            // 이미지 엔티티 리스트를 dto로 변환
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        //해당 id의 상품정보를 데이터베이스에서 가져옵니다. 없으면 예외처리
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        // 상품 정보를 itemFormDto로 변환
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        // 상품정보 dto에 이미 정보 dto 리스트를 설정
        itemFormDto.setItemImgDtoList(itemImgDtoList);

        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        // 상품 수정
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        // x. 상품 등록 화면으로부터 전달받은 상품 아이디를 이용하여 상품 엔티티 조회
        item.updateItem(itemFormDto);

        // 2. 상품 등록 화면으로부터 전달받은 itemFormDto를 통해 상품 엔티티 업데이트
        //itemFormDto에서 항목 이미지 Id 목록을 가져옵니다.
        // (상품 이미지 저장)
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        // 이미지 등록
        // itemImgFileList를 반복하면서 각 이미지에 대해 itemService의 updateItemImg 메서드를 호출합니다.
        for (int i = 0; i < itemImgFileList.size(); i++) {
            // get(i) = List 나 배열에서 i에 해당하는 요소를 가져오는 메소드
            // get(0) 첫 번째 요소

            // DB 저장시 공백 제거
            if (!StringUtils.isEmpty(itemImgFileList.get(i).getOriginalFilename())) {
                itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
            }
        }
        // 상품 이미지 업데이트를 통해 updateItemImg 메소드;
        // 상품이미지아이디와 상품이미지 파일정보를 레포지토리로 전달

        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

}
