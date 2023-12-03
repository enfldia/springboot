package ac.controller;

import ac.dto.ArticleFormDto;
import ac.entity.Article;
import ac.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class Comtroller {

    private final ArticleRepository articleRepository;

    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping("/articles/new")
    public String articleForm(){
        return "article/articleForm";
    }

    @PostMapping("/articles/new")
    public String create(ArticleFormDto articleFormDto){
        Article article = articleFormDto.toEntity();
        Article saved = articleRepository.save(article);
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",article);
        return "article/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList",articleList);
        return "article/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        //모델로
        model.addAttribute("article", article);
        //뷰로
        return "article/editForm";
    }
    @PostMapping("/articles/update")
    public String update(ArticleFormDto articleFormDto){
        //디티오 - 엔티티
        Article article = articleFormDto.toEntity();
        //2 저장
        //2-1 기존데이터 가져옴
        Article target = articleRepository.findById(articleFormDto.getId()).orElse(null);
        //2-2 기존 데이터가 있으면 값 갱신
        if(target != null){
            articleRepository.save(article);
        }
        //3 수정 결과 페이지로 리다이랙트
        return "redirect:/articles/" + article.getId();

    }
}
