package com.shop.repository;

import com.shop.entity.Faq;
import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<Faq, Long> {

}
