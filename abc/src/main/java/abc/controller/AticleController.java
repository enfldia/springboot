package abc.controller;

import abc.dto.ArticleFormDto;
import abc.entity.Article;
import abc.repository.ArticleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AticleController {
    private final ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newForm(){
        return "article/articleForm";
    }

    @PostMapping("/articles/create")
    public String create(ArticleFormDto newFormDto){
        // dto를 entity로 변환
        Article article = newFormDto.toEntity();
        //repository에게 entity를 db로 저장하게 함
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "redirect:/articles/"+ saved.getId();
    }


    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        // 1: id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2: 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        // 3: 보여줄 페이지를 설정
        return "article/show";
    }


    @GetMapping("/articles")
    public String index(Model model){
        // 모든 article 을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 가져온 article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        //뷰페이지 설정
        return "article/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        //수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        //모델
        model.addAttribute("article",article);
        //뷰
        return "/article/editForm";

    }

    @PostMapping("/articles/update")
    public String update(ArticleFormDto articleFormDto){
        //디티오 -엔티티
        Article article = articleFormDto.toEntity();
        //기존 값 타겟에 담음
        Article target = articleRepository.findById(articleFormDto.getId()).orElse(null);
        //2저장
        //2-1 기존 값이 존재하면 저장 없으면 저장 없이 넘어감
        if(target != null ){
            articleRepository.save(article);
        }
        return "redirect:/articles/"+ article.getId();
    }
}
