package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j // 로깅을 위한 롬복 어노테이션
public class ArticleController {
    private final ArticleRepository articleRepository;

    private final CommentService commentService;

//    @Autowired
//    public ArticleController(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }

//    @Autowired
//    private ArticleRepository articleRepository;
//    필드 주입. 간단
//    테스트 어려운
//    결합도가 높아서 유지보수 어려움

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // 방법1
        //System.out.println(form.toString());
        log.info(form.toString());
        // 1. Dto를 Entity 변환
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());
        // 2. Repository에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        // 방법2
//        System.out.println(form.toString());
//        Article article = new Article();
//        // form에 있는 것은 get으로 불러 와서 article에서 set으로 설정한다.
//        article.setTitle(form.getTitle());
//        article.setContent(form.getTitle());
//        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());

        // redirect: 생성 후, 브라우저가 해당 url로 재요청
        return "redirect:/articles/" + saved.getId();

    }

    @GetMapping("/articles/{id}") // 해당 url 요청 처리
    public String show(@PathVariable Long id, Model model) { // url에서 id를 변수로 가져옴
        log.info("id = " + id);

        // 1. id로 이용해서 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos =  commentService.comments(id);
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentsDtos);
        // 보여줄 페이지를 설정
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article에 있는 내용을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 뷰로 전달한다
        model.addAttribute("articleList", articleEntityList);
        log.info("리스트의 총갯수는?. Article {}", articleEntityList.size());// log.info로 로깅
        // 3. 뷰페이지를 설정한다.
        return "articles/index";
    }


    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 1. 수정할 데이터 가져오기 articleEntity
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델 데이터 등록
        model.addAttribute("article", articleEntity);
        // 3. 뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        // 1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString()); // @Slf4j 필요
        // 2. Entity를 DB로 저장
        // 2-1. DB에서 기존 데이터를 가져옴
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존에 데이터가 있다면, 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }


    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // RedirectAttributes: 리다이렉트 후 다른 컨트롤러나 뷰로 데이터를 전달할 때 쓰임
        log.info("삭제 요청이 들어왔습니다.");
        // 1. 삭제 대상을 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상을 삭제
        if (target != null) {
            articleRepository.delete(target);
            // addFlashAttribute: 전달하고 끝! 데이터 저장 x
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }
        return "redirect:/articles";
    }


}
