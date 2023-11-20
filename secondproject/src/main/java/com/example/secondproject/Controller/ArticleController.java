package com.example.secondproject.Controller;

import com.example.secondproject.DTO.AritcleForm;
import com.example.secondproject.Entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.service.ArticleService;
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
@RequiredArgsConstructor //@RequiredArgsConstructor어노테이션은 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션입니다.
@Slf4j
public class ArticleController {
    private final ArticleRepository articleRepository; //의존성 부여
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
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

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles")    //아티클에 저장된 파일 갯수 확인
    public String index(Model model){
        //1. 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
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
        model.addAttribute("article",articleEntity);
        //"article"이라는 것에 aritcleEntuty 값을 대입한다.
        // 3. 뷰 페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles/{id}/edit")
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
    @PostMapping("/articles/update") //데이터 수정하기
    public String update(AritcleForm form){ //ArticleForm 타입(여기선 DTO)의 데이터를 매개변수 form으로 받는다.
        //1.DTO를 엔티티로 변환
        Article articleEntity = form.toEntity(); //form을 Entity로 변환하고 articleEntity에 대입
        //2.엔티티를 DB로 저장
            //2-1 DB에서 기존 데이터를 가져옴
            Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
            // articleEntity의 id 값을 가져와 articleRepository의 findById 메소드로 DB에서 같은 값을 찾고,그걸 Article 타입의 target에 대입
            //Article은 optional이기 때문에 orElse,orElseGet 등으로 마무리 해줘야한다.

            //2-2 기존 데이터가 있다면 값을 갱신
            if(target != null){     //만약 타겟이 null이 아니라면
                articleRepository.save(articleEntity);  //articleEntity의 값을 articleReoisitory의 save 메소드로 DB에 저장
            }
            //수정 결과를 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }
    @GetMapping("/article/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //리다이렉트 후 다른 컨트롤러나 뷰로 데이터를 전달할 때 쓰임
        log.info("삭제 요청이 들어왔습니다.");

        //1. 삭제 대상 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상 삭제
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
