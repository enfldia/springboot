package com.shop.controller;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/admin/item/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto",new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile")List<MultipartFile> itemImgFileList){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "item/itemForm";
        }

        try{
            itemService.saveItem(itemFormDto, itemImgFileList);
        }catch (Exception e) {
            model.addAttribute("errorMessage","상품 등록 중 에러가 발생했습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
    @GetMapping("/admin/item/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId,Model model){
        try{
            ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDto",itemFormDto);
            //조회한 상품 데이터를 모델에 담아서 뷰로 전달
        }catch(EntityNotFoundException e){
            //상품 엔티티가 존재하지 않으면 에러 메시지를 담아서 상품 등록 페이지로 전달하고,
            model.addAttribute("errorMessage","존재하지 않는 상품입니다.");
            //새로운 상품을 등록할 수 있게 새로운 아이템폼 디티오를 만들고
            model.addAttribute("itemFormDto",new ItemFormDto());
            return "item/itemForm";

        } //등록 페이지로 이동
        return "item/itemForm";
    }

    @PostMapping ("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto,BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,Model model){
        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "item/itemForm";
        }

        try{
            itemService.saveItem(itemFormDto, itemImgFileList);
            //itemFormDto- 상품 정보 itemImgFileList- 상품 이미지 정보
        }catch (Exception e) {
            model.addAttribute("errorMessage","상품 등록 중 에러가 발생했습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
    @GetMapping({"/admin/items","/admin/items/{page}"})
    public String itemManege(ItemSearchDto itemSearchDto,
                             @PathVariable("page") Optional<Integer> page, Model model){
        Pageable pageable = PageRequest.of(page.isPresent()? page.get() : 0,3);
        // PageRequest - Data jpa 에서 사용하는 페이지 요청 객체
        // of() 메서드를 사용하여 페이지 번호화 페이지 당 항목 수 지정하여 페이지 요청 정보 생성
        // 0 은 url 경로에서 받아온 페이지 번호를 확인하고, 값이 없으면 0
        // 3 은 한 페이지 당 보여 줄 항목 수

        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);
        // itemSearchDto 를 사용하여 페이지 네이션 된 데이터를 조회
        model.addAttribute("items",items);//조회된 페이지 네이션 데이터 모델 추가
        model.addAttribute("itemSearchDto", itemSearchDto); //검색조건 모델에 추가
        model.addAttribute("maxPage", 5); //한번에 표시할 최대 페이지 수를 모델에 추가
        //이 값을 템플릿에서 사용하여 페이지 네이션 UI를 그릴떄(렌더링) 활용
        return  "item/itemMng";
    }
}