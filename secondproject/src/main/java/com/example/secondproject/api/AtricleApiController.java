package com.example.secondproject.api;

import com.example.secondproject.DTO.AritcleForm;
import com.example.secondproject.Entity.Article;
import com.example.secondproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;

@RestController //@RestController는 @Controller에 @ResponseBody가 추가된 것입니다. 당연하게도 RestController의 주용도는 Json 형태로 객체 데이터를 반환하는 것입니다.
@Slf4j
@RequiredArgsConstructor
public class AtricleApiController {
    private final ArticleService articleService;

    //게시글 페이지(메인 페이지)를 만들기 위해 List형식으로 받음
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    //게시글 상세 페이지(단건 조회)
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //게시글 추가 작성
    @PostMapping("/api/articles")
    //ResponseEntity는 HttpEntity를 상속받음으로써 HttpHeader와 body를 가질 수 있고, 상태 코드와 응답 메세지 등을 포함된다.
    public ResponseEntity<Article> create(@RequestBody AritcleForm dto){
        // JSON 값을 받기 위해 아티클 형태의 ResponseEntity 메소드 create 를 만드는데,아티클 폼의 dto를 매개변수로 RequsetBody로 요청하고 바디에서 받아 온다.
        Article create = articleService.create(dto);
        //받아온 dto를 이티클 서비스의 create를 실행하고 그 값을 Article 형태의 create에 대입한다.
        //아티클 서비스 크리에이트로 넘어간 dto는 엔티티화된 후 아이디 값이 null인 경우 아티클레포지토리의 세이브 메서드로 데이터 베이스에 저장된다.
        return (create != null)?
                //create 의 값이 null이 아니라면
            ResponseEntity.status(HttpStatus.OK).body(create) :
                //리스폰스엔티티로 http에 응답을 하게되는데 .status에는 HttpStatus.ok 로 정상의 상태로, body에는 create 값을 전달한다.
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                //반대로 null 값을 가지면 리스폰스엔티티로 http에 HttpStatus.BAD_REQUEST 로 오류 상태를, build()로 빈 값을 전달한다.
    }

    //게시글 수정
    @PatchMapping("/api/articles/{id}")
    //게시글을 수정하기위해 게시글의 id값을 주소에서 패스배리어블로 받아온다.
    //리퀘스트바디로 http의 바디 값을 요청해서 아티클폼 형태의 dto로 받아온다.
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody AritcleForm dto){
        //받아온 id와 dto를 아티클 서비스의 업데이트로 보낸다.
        //보낸 값으로 dto를 entity화 하고, 아이디로 DB안에 같은 아이디를 같는 게시물을 값을 target이라는 변수로 가져온다.
        //target이 null 이거나, 입력 받은 아이디와 아티클에서 가져온 아이디 값이 다른 잘못된 요청이 들어오면 null을 리턴시키게하고.
        //target.patch(article)로 기존 타겟에 아티클의 값을 패치한다.
        //그리고 articleRepository.save(target);으로 저장후 그 값을 리턴한다.
        Article update = articleService.update(id,dto);
        //리턴 받은 값이 null이 아니면
        return (update != null)?
                //리스폰스엔티티로 http에 응답을 하는데.스테이터스 값으로 정상을 나타내는 HttpStatus.OK 를 보내고, 바디에는 리턴 받은 값을 보낸다.
                ResponseEntity.status(HttpStatus.OK).body(update) :
                //널이라면 잘못된 요청이라는 HttpStatys.BAD_REQUEST 를 보내고, build()로 빈 값을 보낸다.
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article delete = articleService.delete(id);
        return (delete != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<AritcleForm> dtos){
        //아티클을 리스트(묶음 단위 전송), 리스폰스엔티티로 감싸서 받음
        List<Article> createList = articleService.createArticle(dtos);

        return (createList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
