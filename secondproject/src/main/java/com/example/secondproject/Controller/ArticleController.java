package com.example.secondproject.Controller;

import com.example.secondproject.DTO.AritcleForm;
import com.example.secondproject.Entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor //@RequiredArgsConstructor어노테이션은 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션입니다.
public class ArticleController {
    private final ArticleRepository articleRepository; //의존성 부여
    @GetMapping("/article/new")
    public String newArticleForm(){
        return "article/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(AritcleForm form){
        //1. DTO를 Entity 변환
        System.out.println(form.toString()); // form 상태의 데이터
        Article article = form.toEntity(); //form 데이터를 article에 대입
        System.out.println(article.toString()); //article 상태의 데이터
        //2. Repository 에게 Entity를 DB로 저장하게함
        Article saved = articleRepository.save(article); //article 값을 articleRepository의 save 메소드를 이용해서 article 형태의 saved라는 객체에 대입
        System.out.println(saved.toString()); //저장된 상태의 데이터

        return "redirect:/articles/" ;
    }

    @GetMapping("/aritcles")    //아티클에 저장된 파일 갯수 확인
    public String index(Model model){
        //1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList.size());
        System.out.println("리스트의 총갯수는 ? articles{"+articleEntityList.size()+"}");
        //3. 뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}")   // 해당 아이디가  갖고 있는 값을 출력
    public String show(@PathVariable Long id,Model model){ //@PathVariable 은 겟맵핑 url에 들어있는 변수를 가져오는 어노테이션
        System.out.println("id = " + id);
        // 1. id로 이용해서 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //articleRepository의 findById 메소드를 id의 값으로 아이디를 찾는데,값이 없다면 디폴트 값으로 null ;
        //찾은 값을 Article 이라는 데이터 타입의 article Entity 라는 변수이 대입
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("aritcle",articleEntity);
        //"article"이라는 것에 aritcleEntuty 값을 대입한다.
        // 3. 뷰 페이지 설정
        return "articles/show";
    }

    @GetMapping("/article/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        // 1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //url에서 전달 받은 id를 아티클레포지토리의 파인드바이 아이디 메소드로 값을 찾는다.없다면 null을 받는다.
        //그 값을 아티클 타입의 아티클엔티티라는 변수에 대입한다.
        // 2. 모델에 데이터를 등록
        model.addAttribute("article", articleEntity);
        //아티클엔티티의 값을 모델 에드어트리뷰트 메소드를 이용해서 "아티클"에 대입한다.
        // 3. 뷰 페이지 설정
        return "articles/edit";
    }
}
