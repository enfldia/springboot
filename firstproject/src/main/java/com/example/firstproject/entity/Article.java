package com.example.firstproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue //save 가 될때 jpa가 자동으로 id 값을 대입한다.그러므로 id를 값을 안 넣어도 생성된다.
    private  Long id;
    private String title;
    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
