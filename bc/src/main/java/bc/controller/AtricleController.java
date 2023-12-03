package bc.controller;

import bc.dto.ArticleFormDto;
import bc.entity.Article;
import bc.repository.ArticleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AtricleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/")
    public String main(){
        return "/main";
    }

    @GetMapping("/articles/new")
    public String articleForm(){
        return "article/articleForm";
    }

    @PostMapping("/articles/new")
    public String create(ArticleFormDto articleFormDto){
        Article article = articleFormDto.toEntity();
        Article saved = articleRepository.save(article);
        return "redirect:/articles/"+article.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "article/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList",articleList);
        System.out.println(articleList.toString());
        return "/article/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String editForm(@PathVariable Long id,Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "/article/editForm";
    }

    @PostMapping("/articles/update")
    public String update(ArticleFormDto articleFormDto){
        Article article = articleFormDto.toEntity();
        Article target = articleRepository.findById(articleFormDto.getId()).orElse(null);
        if(target != null){
            articleRepository.save(article);
        }
        return "redirect:/articles/"+article.getId();
    }
}
