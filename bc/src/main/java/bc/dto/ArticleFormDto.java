package bc.dto;

import bc.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

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
