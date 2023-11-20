package com.shop.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    //AuditorAware는 스프링데이터JPA에서 Auditing에 사용되는 인터페이스이다. 이 인터페이스는 현재 사용자를 식별하고 생성자 및 수정자와 같은 감시 정보를 제공하는 역할을 한다.

    //Optional<타입> getCurrentAuditor() : 현재 사용자를 나타내는 <타입>의 객체를 반환한다. <타입>은 사용자의 식별자, 이름 또는 사용자 객체와 같은 것일 수 있다.
    // Optional은 현재 사용자가 존재하지 않을 수 있음을 나타낸다.

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = ""; //현재 사용자를 가져오는 로기
        if(authentication != null){
            userId = authentication.getName();
            //현재 로그인한 사용자의 정보를 조회하여 사용자의 이름을 등록자와 수정자로 지정합니다.
        }
        return Optional.empty();
    }
}
//AuditorAware 인터페이스를 구현 AuditorAwareImpl
//현재 사용자를 식별할떄 사용
//SecurityContextHolder.getContext().getAuthentication(); - 현재의 인증정보에 접근
//authentication.getName(); 현재 사용자의 이름을 가져온다.
//현재 사용자를 찾을 수 없다면 빈 Optional을 반환합니다.
//주로 감사(Audit)와 생성자/수정자의 정보를 데이터베이스 테이블에 기록할 때 사용됩니다.