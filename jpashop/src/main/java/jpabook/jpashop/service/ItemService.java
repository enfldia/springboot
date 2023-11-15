package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long itmeId){
        return itemRepository.findOne(itmeId);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }
    //itemId를 통해 item을 가지고 오면 영속성 객체 item이 블러온다.
    //즉, 영속성 객체 자체에 값을 넣기 때문에 변경사항이 알아서 반영된다.

    //본인이 원하는 값만 set으로 넣게 되면 @Transactional이 되면서
    //em.flush 가 자동으로 되며 DB에 반영

    //영속성을 사용하려면 조건이 있다. service에서 transactional을 사용하며 쓴다.
}
