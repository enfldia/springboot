package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public  void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAllByString(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if(orderSearch.getOrderStatus() != null){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";

        }
        //회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            if(isFirstCondition){
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql = "and";
            }
            jpql += " m.name like :mane";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);
        if(orderSearch.getOrderStatus() != null){
            query = query.setParameter("status",orderSearch.getOrderStatus());
        }//만약 주문상태가 주어졌다면 status 파라미터를 설정
        if(StringUtils.hasText(orderSearch.getMemberName())){
            query = query.setParameter("name",orderSearch.getMemberName());
        }//회원 이름이 있다면 name 파라미터 설정

        return query.getResultList();
    }
}
