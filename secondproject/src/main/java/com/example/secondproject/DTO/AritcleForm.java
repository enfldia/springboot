package com.example.secondproject.DTO;

import com.example.secondproject.Entity.Article;

public class AritcleForm {
    private String title;
    private String content;
    private String nickName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "AritcleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Article toEntity(){
        return new Article(null, nickName, title, content);
    }
}
