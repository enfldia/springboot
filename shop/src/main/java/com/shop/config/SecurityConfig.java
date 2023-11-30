package com.shop.config;


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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .failureUrl("/members/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                .logoutSuccessUrl("/")

        ;

        http.authorizeRequests()
                .mvcMatchers("/css/**","/js/**","/img/**").permitAll() /*premitAll() -은 모든 사용자 접근 가능*/
                .mvcMatchers("/","/members/**","/item/**","/images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN") /* ADMIN 권한을 가진 사용자만 접근 가능 */
                .anyRequest().authenticated();
    //static 밑에 css, js, img 폴더는 접근 권한을 줌
    //"/","/members/**","/item/**","/images/**" 모든 사용자에게 접근 허용

        //http.authorizeRequests() 권한부여
        //.anyRequest() 모든 요청에 권한설정()
        //.authenticated(): 인증된 사용자만이 해당 요청에 접근
        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        return  http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}