package com.example.secondproject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String title;
    private String content;

    public Article(Long id, String nickname, String title, String content) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Aricle{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

