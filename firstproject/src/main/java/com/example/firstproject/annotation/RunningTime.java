package com.example.firstproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD}) //어노테이션의 적용대상
@Retention(RetentionPolicy.RUNTIME) //어노테이션의 유지기간
public @interface RunningTime {

}

// @RunningTime 이라는 사용자 어노테이션 정리
// @Target: 대상 지정
// ElementType 은 클래스, 인터페이스, 열거형타입에 적용될 수 있다.
// @interface RunningTime 어노테이션 사용하려면 메소드나 클래스위에 @RunningTime - 수행 시간

