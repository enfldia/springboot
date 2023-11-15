package jpabook.jpashop.comtroller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.web.BookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return  "items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String cretae(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

        return "redirect:/items";
    }
    @GetMapping(value = "/items")
    public String List(Model model){
       List<Item> items = itemService.findItems();
       model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,
                                  Model model){
        Book item = (Book)itemService.findOne(itemId);
        BookForm form = new BookForm();

        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setAuthor(item.getAuthor());
        form.setStockQuantity(item.getStockQuantity());
        form.setIsbn(item.getIsbn());
        model.addAttribute("form",form);

        return "items/updateItemForm";

    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId,@ModelAttribute("form") BookForm form){
//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//        itemService.saveItem(book);
//        병합 방식

        itemService.updateItem(itemId,form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}

//병합(merge)은 준영속 엔티티이다.
//준영속 엔티티는 영속성 엔티티가 더 이상 관리하지 않는 엔티티이며,
//persist 되어서 식별자가 있는 경우를 뜻한다.
//값이 변경되어도 알아서 반영이 되지 않기 때문에 따로 값을 반영해야한다.

//파라미터 item 객체는 준영속 엔티티이며 em.merge를 통해
//Item merge는 파라미터로 받은 item 으로 값들이 다 교체된다.

//merge 할 경우 강제적으로 모든 필드의 값을 교체되며 값이 없으면
//null 로 들어가기 떄문에 조심해야 한다.

//merge 할 경우 강제적으로 모든 필드의 값들이 변경되기 때문에
//"엔티티를 변경할 때는 항상 변경감지를 사용한다"

//@Controller 에서 식별자(primary Key)만 남기고 핵심 비즈니스 로직은
//@Transactional(@Service) 안에서 처리하는 것이 좋다.