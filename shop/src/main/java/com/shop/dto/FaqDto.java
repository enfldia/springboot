package com.shop.dto;

import com.shop.constant.FaqType;
import com.shop.entity.Faq;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
public class FaqDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private FaqType faqType;
    private String faqQuestion;
    private String faqAnswer;

    public Faq toEntity() {
        Faq faq = new Faq();
        faq.setId(this.id);
        faq.setFaqType(this.faqType);
        faq.setFaqQuestion(this.faqQuestion);
        faq.setFaqAnswer(this.faqAnswer);
        return faq;
    }
}
