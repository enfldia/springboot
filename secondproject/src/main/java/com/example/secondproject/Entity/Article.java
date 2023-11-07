package com.example.secondproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa의 어노테이션으로 엔티티 선언
@AllArgsConstructor // 모든 필드를 갖는 생성자 생성 어노테이션
@ToString           // toString 어노테이션
@NoArgsConstructor  // 필드가 없는 빈 생성자 생성 어노테이션
@Getter             // 게터 생성 어노테이션
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //save 가 될때 jpa가 자동으로 id 값을 대입한다.그러므로 id를 값을 안 넣어도 생성된다.
    private Long id;
    private String nickname;
    private String title;
    private String content;

    public void patch(Article article){
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }

}

