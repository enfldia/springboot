package com.shop.service;

import com.shop.dto.CartDetailDto;
import com.shop.dto.CartItemDto;
import com.shop.dto.CartOrderDto;
import com.shop.dto.OrderDto;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Item;
import com.shop.entity.Member;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.shop.entity.QCartItem.cartItem;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemDto cartItemDto, String email){
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        // 장바구니에 담을 상품 엔티티를 받은 인자값인 cartItemDto로 조회해서 아이템이 넣는다.
        Member member = memberRepository.findByEmail(email);
        // 현재 로그인한 회원 엔티티를 받은 인자값인 email로 조회
        Cart cart = cartRepository.findByMemberId(member.getId());
        // 현재 로그인한 회원의 장바구니 엔티티를 조회해서
            if(cart==null){
                // 상품을 처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티 생성
                cart = Cart.createCart(member);
                cartRepository.save(cart);
            }
            CartItem saveCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(),item.getId());
            // 현재 상품이 장바구니에 이미 들어가 있는지를 조회
        if(saveCartItem != null){
            saveCartItem.addCount(cartItemDto.getCount());
            // 이미 있던 상품일 경우 기존 수량에 현재 장바구니에 담을 수량만큼 더 해준다.
            return saveCartItem.getId();
        }else{
            CartItem cartItem = CartItem.creteCartItem(cart, item, cartItemDto.getCount());
            // 장바구니 엔티티와 상품 엔티티, 장바구니에 담을 수량을 이용해서 cartItem 엔티티를 생성
            cartItemRepository.save(cartItem);
            // 장바구니에 들어갈 상품을 저장
            return cartItem.getId();
        }

    }
    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){
        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());
        // 현재 로그인한 회원의 장바구니 엔티티를 조회합니다.
        if(cart == null){ // 장바구니에 상품을 한번도 안 담을 경우
            return cartDetailDtoList; // 빈 리스트를 반환
        }
        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        // 장바구니의 상품 정보를 조회해서 반환
        return cartDetailDtoList;
    }

    @Transactional(readOnly = true)
    public boolean validateCartItem(Long cartItemId,String email){
        Member curMember = memberRepository.findByEmail(email);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        Member savedMember = cartItem.getCart().getMember();
        //cartItem 객체가 속하는 cart 객체와 연결된 Member 객체를 가져옵니다.
        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }
        return true;
    }
    public void updateCartItemCount(Long cartItemId, int count){
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(EntityNotFoundException::new);
        cartItem.updateCount(count);
    }

    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }

    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email){
        List<OrderDto> orderDtoList = new ArrayList<>();
        //orderDtoList -> 주문 정보를 저장할 목적
        for(CartOrderDto cartOrderDto : cartOrderDtoList){
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            //cartItem 조회
            OrderDto orderDto = new OrderDto();
            orderDto.setItemId(cartItem.getItem().getId());
            orderDto.setCount(cartItem.getCount());
            orderDtoList.add(orderDto);
        }
        Long orderId = orderService.orders(orderDtoList, email);
        for(CartOrderDto cartOrderDto : cartOrderDtoList){
            CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                    .orElseThrow(EntityNotFoundException::new);
            cartItemRepository.delete(cartItem);
            // 장바구니 주문을하고 장바구니 삭제
        }
        return orderId;
    }
}
