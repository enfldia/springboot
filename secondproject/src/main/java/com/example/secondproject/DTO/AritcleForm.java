package com.example.secondproject.DTO;

import com.example.secondproject.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@ToString
public class AritcleForm {
    private Long id;
    private String nickname;
    private String title;
    private String content;



    public Article toEntity(){
        return new Article(id, nickname, title, content);
    }
}
