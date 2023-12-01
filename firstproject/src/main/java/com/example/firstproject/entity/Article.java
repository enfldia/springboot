package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor // 사용하면 NoArgsConstructor 써야 함
@NoArgsConstructor // 디폴트 생성자 추가
@ToString
@Getter
public class Article {
    @Id // id가 primary로 사용되려면 사용해야 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 전략
    private Long id;
    private String title;
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }


//    public Article() {} => @NoArgsConstructor 이 대체


    /*   =>AllArgsConstructor 이 대체
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    */

     /*   ==> @ToString이 대체
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
    */

}
