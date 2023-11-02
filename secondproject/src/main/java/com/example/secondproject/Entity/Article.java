package com.example.secondproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //jpa의 어노테이션으로 엔티티 선언
@AllArgsConstructor // 모든 필드를 갖는 생성자 생성 어노테이션
@ToString           // toString 어노테이션
@NoArgsConstructor  // 필드가 없는 빈 생성자 생성 어노테이션
@Getter             // 게터 생성 어노테이션
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String title;
    private String content;

}

