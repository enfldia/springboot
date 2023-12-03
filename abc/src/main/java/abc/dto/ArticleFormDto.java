package abc.dto;

import abc.entity.Article;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ArticleFormDto {

    private Long id;

    private String title;

    private String content;

    public ArticleFormDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Article toEntity(){
        return new Article(id, title, content);
    }

}
