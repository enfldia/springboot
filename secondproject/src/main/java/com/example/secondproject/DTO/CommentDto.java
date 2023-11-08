package com.example.secondproject.DTO;

import com.example.secondproject.Entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    @JsonProperty("article_id")
    //자바는 카멜표기법,json은 스네이크 표기법을 사용하는데.변수의 이름을 매핑하는 어노테이션이다.
    // json에선 article_id로 자바에선 articleId로 변수를 사용한다.
    private Long articleId;
    private String body;
    private String nickName;

    public static CommentDto createCommentDto(Comment comment){
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
