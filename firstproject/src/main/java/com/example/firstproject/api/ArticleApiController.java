package com.example.firstproject.api;

import com.example.firstproject.DTO.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@Slf4j
@RequiredArgsConstructor
public class ArticleApiController {
//    private final ArticleService articleService;
    private final ArticleService articleService;
//    @Autowired
//    public ArticleApiController(ArticleService articleService) {
//        this.articleService = articleService;
//    } ==> @RequiredArgsConstructor 가 대체

//    @Autowired
//    private ArticleService articleService; ==< 필드 주입 방식

    //Get
    //게시글 페이지(메인 페이지)를 만들기 위해 list형식으로 받음
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    //게시글 상세 페이지(단건 조회)
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleService.show(id);
    }

    //post(게시글 추가(새로 작성))
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
          Article create = articleService.create(dto);
          return (create != null)?
                  ResponseEntity.status(HttpStatus.OK).body(create) :
                  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //@RequestBody -> Json 데이터 받기


    //patch(update = 게시글 수정)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        //수정할 id ->  id
        //article form에서 수정한 데이터
        Article updated = articleService.update(id,dto);
        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //ResponseEntity<Article>
    //Article을 담아서 ResponseEntity로 리턴 값을 보내야한다.
    //응답코드를 반환 할 수 있다.
    //ResoponseEntity 의 Article 에 담겨서 JSON으로 반환
    // 상태코드 200을 JSON에
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article deleted = articleService.delete(id);
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //ResponseEntity.status(HttpStatus.NO_CONTENT).build() - 204 no_content
    // 요청이 성공적으로 처리 되었음을 알린다. 또한 응답 본문에는 데이터락 반환되지 않는다.
    // 코드 추력

    //트렌젝션 -> 예외 발생 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        // Article을 리스트(묶음 단위 전송), ResponseEntity로 감싸서 받음
        List<Article> createList = articleService.createArticle(dtos);

        return (createList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
