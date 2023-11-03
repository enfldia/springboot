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
    private final ArticleService articleService;


    @Autowired
    private  ArticleRepository articleRepository;

    //Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
            Article article = dto.toEntity();
            return articleRepository.save(article);
    }

    //patch(update)
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        //1. DTO 를 엔티티로 변환
        Article article = dto.toEntity(); //수정한 데이터
        log.info("id: {},article: {}",id,article.toString());
        //2. target 조회 -> 수정전 데이터
        Article target =articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리
        if(target ==null || id != article.getId()){ //{id}와 article.getId의 값이 다르면 404를 출력
            //400 잘못된 요청 응답
            log.info("잘못된 요청! id:{} ,article: {}", id,article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. 업데이트 및 정상응답(200)
        target.patch(article);
        Article update = articleRepository.save(target);
        return  ResponseEntity.status(HttpStatus.OK).body(update);

    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //대상 삭제 후 HttpStatus.OK 상태 코드 받기
        articleRepository.delete(target);
        return  ResponseEntity.status(HttpStatus.OK).build();
        //build()는 보낼 내용이 없을 때 쓰는 내용이 비어있는 응답

    }
}
