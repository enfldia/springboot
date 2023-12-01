package com.shop.entity;

import com.shop.constant.FaqType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "faq")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "faq_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FaqType faqType;

    @Column(length = 1000)
    private String faqQuestion;

    @Column(length = 5000)
    private String faqAnswer;

    public Faq toDto() {
        Faq faq = new Faq();
        faq.setId(this.id);
        faq.setFaqType(this.faqType);
        faq.setFaqQuestion(this.faqQuestion);
        faq.setFaqAnswer(this.faqAnswer);
        return faq;
    }
}
