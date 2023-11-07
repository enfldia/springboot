package com.example.firstproject.controller;

import com.example.firstproject.DTO.ArticleForm;
import com.example.firstproject.DTO.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j //로깅을 위한 룸복 어노테이션 -일단 지금은 sout 대신 사용
public class ArticleController {
    private final ArticleRepository articleRepository;
//    @Autowired
//    public ArticleController(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }

//    @Autowired
//    private ArticleRepository articleRepository;
//    //필드 주입 간단.
//    //테스트 어려움
//    //결합도가 높아서 유지보수 어려움

    private final CommentService commentService;


    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
//        System.out.println(form.toString());
        //1. DTO를 Entity 변환
        log.info(form.toString());
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());
        //2. Repository 에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());

//        System.out.println(form.toString());
//        Article article = new Article();
//        article.setTitle(form.getTitle());
//        article.setContent(form.getcontent());
//        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }


    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져온다
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        log.info("리스트의 총갯수는?. Articles {}", articleEntityList.size());
        // 3. 뷰페이지를 설정
        return "articles/index";
    }
    @GetMapping("/articles/{id}")   // 해당 url 요청처리
    public String show(@PathVariable Long id,Model model){  // URL에서 id를 변수로 가져오는 어노테이션
        log.info("id = " + id);

        // 1. id로 이용해서 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentsDtos);
        //보여줄 페이지를 설정

        return "articles/show";

    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기 articleEntity
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델 데이터 등록
        model.addAttribute("article",articleEntity);
        //뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1.DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2.엔티티를 DB로 저장
            //2-1 : DB에서 기존 데이터를 가져옴
            Article target = articleRepository.findById(articleEntity.getId())
                    .orElse(null);
            //2-2 : 기존에 데이터가 있다면, 값을 갱신
            if(target != null){
                articleRepository.save(articleEntity);
            }
            //수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
        public String delete(@PathVariable Long id, RedirectAttributes rttr){ //<- 삭제의 시그니쳐 구성
        //리다이렉트 후 다른 컨트롤러나 뷰로 데이터를 전달할 때 쓰임
            log.info("삭제 요청이 들어왔습니다.");

            // 1. 삭제 대상 가져옴
            Article target = articleRepository.findById(id).orElse(null);
            log.info(target.toString());

            // 2. 대상 삭제
            if(target != null){
                articleRepository.delete(target);
                rttr.addFlashAttribute("msg","삭제가 완료되었습니다.");
                //rttr.addFlashAttribute로 전달한 값은 url뒤에 붙지 않는다.
                //일회성이라 리프레시할 경우 데이터가 소멸한다.
                //또한 2개이상 쓸 경우, 데이터는 소멸한다.

            }
            return "redirect:/articles";
        }

}
