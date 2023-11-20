package com.shop.repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.shop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
