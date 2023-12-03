package ac.dto;

import ac.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticleFormDto {

    private Long id;

    private String title;

    private String content;

    public Article toEntity(){
        return new Article(id,title,content);
    }
}
