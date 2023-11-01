package com.example.firstproject.DTO;


import com.example.firstproject.entity.Article;

public class ArticleForm {
    private String title;
    private String content;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String contnet) {
        this.content = contnet;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", contnet='" + content + '\'' +
                '}';
    }

    public Article toEntity(){
        return new Article(null, title, content);

    }
}
