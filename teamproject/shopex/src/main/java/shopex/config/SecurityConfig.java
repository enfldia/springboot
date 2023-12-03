package shopex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //SecurityFilterChain 타입으로 HttpSecurity http를 인자값으로 받는 filterChain 매소드 생성.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login") //스프링에서 제공하는 login 페이지가 아니라 사용자가 커스텀한 페이지 주소
                .defaultSuccessUrl("/") //정상적으로 인증시 이동하는 페이지
                .usernameParameter("email") // 아아디 파라메타를 email로 설정
                .failureUrl("/members/login/error") // 인증 실패 시 이동하는 페이지
                .and()
                .logout() //로그아웃 설정을 위한 logoutConfigure을 리턴
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/") // 로그아웃 시 이동하는 페이지
        ;

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .mvcMatchers("/", "/members/**", "/item/**", "/images/**", "/faq/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        //static 밑에 css, js, img 폴더는 접근 권한을 줌
        //"/", "/members/**", "/item/**", "/images/**" 모든사용자에게 접근허용

        //http.authorizeRequests() 권한부여
        //anyRequest() 모든 요청에 권한설정()
        //authenticated() 인증된 사용자만이 해당 요청에 접근

        http.exceptionHandling() //예외 처리
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //인증 예외(신원 검증)
        ;

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){ //암화화를 위해 bean에 PasswordEncoder를 등록
        return new BCryptPasswordEncoder(); //BCrypt 방식으로 암호화 한다.
    }
}
