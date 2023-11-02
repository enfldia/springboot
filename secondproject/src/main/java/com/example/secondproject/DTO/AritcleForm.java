package com.example.secondproject.DTO;

import com.example.secondproject.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@ToString
public class AritcleForm {
    private String title;
    private String content;
    private String nickName;


    public Article toEntity(){
        return new Article(null, nickName, title, content);
    }
}
