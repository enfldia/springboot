package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //정보를 담당하는 뜻의 파일 , 설정정보 스프링에서는 @Configuration 어노테이션을 적어주게 되어있다.
@EnableJpaAuditing //Auditing을 활성화하려면 @EnableJpaAuditing 어노테이션이 있는 설정 클래스를 작성해야한다.
public class AuditConfig {
    @Bean //스프링 빈은 스프링 컨테이너에 의해 관리되는 자바 객체(POJO)를 의미한다.
    // 각 메서드에 @Bean 을 적어준다.그러면 각 메서드는 스프링 컨테이너에 등록이된다.
    // 등록자와 수정자를 처리해주는 AuditorAware을 빈으로 등록


    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
