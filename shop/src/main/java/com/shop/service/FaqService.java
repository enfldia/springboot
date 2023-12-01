package com.shop.service;

import com.shop.dto.FaqDto;
import com.shop.entity.Faq;
import com.shop.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;
    private EntityManager em;

    public Faq saveFaq(FaqDto faqDto){
        Faq faq = faqDto.toEntity();
        return faqRepository.save(faq);
    }
    @Transactional(readOnly = true)
    public List<Faq> getAllFaqs(){
        return faqRepository.findAll();
    }
}
