package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Controller
@Slf4j
public class ArticleApiController {
//    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    // 객체 주입이 아닌 생성자 주입 권장

    /* => @RequiredArgsConstructor 이 대체함
    @Autowired
    public FirstApiController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    */

//    @Autowired
//    private ArticleService articleService; => 필드 주입 방식

//    @Autowired
//    private ArticleRepository articleRepository; // 임시 => 11/6 수정 예정

    // Get
    // 게시글 페이지(메인 페이지)
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    // 게시글 상세 페이지(단건 조회)
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // Post (create - 게시글 추가)
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null)? // 삼항 연산자
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // @RequestBody -> Json 데이터 받기


    // Patch (update - 게시글 수정)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 수정할 id -> id
        // form에서 수정한 데이터
        Article updated = articleService.update(id, dto);
        return (updated !=null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // ResponseEntity<Article>
    // Article을 담아서 ResponseEntity로 리턴 값을 보내야 한다.
    // 응답코드를 반환할 수 있다.
    //      ResponseEntity에 Article이 담겨서 JSON으로 반환
    // 상태 코드 200 json의 내용이 같이 반환


    // delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted !=null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // ResponseEntity.status(HttpStatus.NO_CONTENT) -> 204 no_content
    // 요청이 성공적으로 처리되었음을 알린다. 또한 응답 본문에는 데이터가 반환되지 않는다.


    // 트랜젝션 -> 예외 발생 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        // Article을 리스트(묶음 단위 전송), ResponseEntity로 감싸서 받음
        List<Article> createList = articleService.createArticle(dtos);
        return (createList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
