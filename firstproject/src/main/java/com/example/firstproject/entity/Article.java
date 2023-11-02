package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {

    @Id
    @GeneratedValue //save 가 될때 jpa가 자동으로 id 값을 대입한다.그러므로 id를 값을 안 넣어도 생성된다.
    private  Long id;
    private String title;
    private String content;

}
