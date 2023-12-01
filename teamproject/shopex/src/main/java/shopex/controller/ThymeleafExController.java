package shopex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shopex.dto.ItemDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafExController {

    @GetMapping("/ex01")
    public String thymeleafExample01(Model model){
        model.addAttribute("data","타임리프 예제입니다.");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model) {
        //1.itemDto 객체 만들기
        ItemDto itemDto = new ItemDto();
        //setter 이용하여 - ItemDetail 상품 상세 설명
        itemDto.setItemDetail("상품 상세 설명");
        // ItemNm - 테스트 상품1
        itemDto.setItemNm("테스트 상품1");
        //Price - 10000
        itemDto.setPrice(10000);
        //RegTime - LocalDateTime.now() 넣어서
        itemDto.setRegTime(LocalDateTime.now());
        //itemDto를 모델로 넘겨서
        model.addAttribute("data", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping("ex03")
    public String thymeleafExample03(Model model) {
        //아이템 디티오를 담을 리스트 생성
        List<ItemDto> itemDtoList = new ArrayList<>();

        //for문으로 10개의 itemDto의 각 값을 생성하고
        for (int i = 1; i <= 10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setItemNm("테스트 상품" + i);
            itemDto.setPrice(1000 * i);
            itemDto.setRegTime(LocalDateTime.now());

            //만든 값을 itemDtoList에 추가
            itemDtoList.add(itemDto);

        }
        //모델로 view단에 itemDtoList를 "itemDtoList"란 변수명으로 보낸다.
        model.addAttribute("itemDtoList",itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping("/ex04")
    public String thymeleafExample04(Model model){

        List<ItemDto> itemDtoList =new ArrayList<>();
        for(int i=1; i<=10;i++){
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setItemNm("테스트 상품"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList",itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping("/ex05")
    public String thymeleafExample05(){
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping("/ex06")
    public String thymeleafExample06(String param1, String param2, Model model){
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleafEx/thymeleafEx06";
    }

    @GetMapping("/ex07")
    public String thymeleafExample07(){
        return "thymeleafEx/thymeleafEx07";
    }

}
