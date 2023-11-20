package com.example.secondproject.service;

import com.example.secondproject.DTO.AritcleForm;
import com.example.secondproject.Entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.lang.model.type.ArrayType;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;


    public Article show(Long id){
        return  articleRepository.findById(id).orElse(null);}


    public Article save (Article article){
        if(article.getId() != null){
            return null;
        }
      return articleRepository.save(article);
    }
    public List<Article> index (){
        /*return articleRepository.findAll();*/
        List<Article> index = articleRepository.findAll();
        return index;

    }

    public Article findById(Long id){
        Article article = articleRepository.findById(id).orElse(null);
        return article;
    }



    public Article create(AritcleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }// 기존 데이터 수정을 방지하기 위해 아이디가 주어져 잇다면 null로 바꾸고 id를 새로 할당하여, 새로 저장
        return articleRepository.save(article);
    }


    public Article update(Long id, AritcleForm dto) {
        //1.dto -> entity
        Article article = dto.toEntity();
        //2. 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        //artciel은 옵셔널 클래스이므로 반환 값을 꼭 설정해 줘야 한다.

        //3. 잘못된 요청 처리
        //만약 타겟이 널이거나 입력 받은 아이디와 아티클에서 가져온 아이디의 값이 다르다면
        if(target == null || id != article.getId()){
            //400, 잘못된 요청 응답
            log.info("잘못된 요청! id{},article{}", id, article.toString());
            return null;
        }
        // 4.업데이트
        // 아티클폼에서 작성한 테이터(수정)
        // target 원본 데이터에서 꺼내온 데이터에 .patch(article)로 아티클의 값을 패치하고
        target.patch(article);
        //아티클 레포지토리의 세이브로 타겟을 저장하고 그 값을 아티클 업데이트에 대입
        Article update = articleRepository.save(target);
        //리턴
        return update;
    }
    public Article delete(Long id) {
        //아이디로 아티클레포지토리.파인드바이아이디로 값을 아티클형태의 타겟에 대입시키고.
        Article target = articleRepository.findById(id).orElse(null);
        //만약 타겟의 값이 null 인 경우 잘못된 요청으로 null 리턴
        if(target == null){
            return null;
        }
        // 데이터 삭제 및 반환
        articleRepository.delete(target);
        return target;

    }


    public List<Article> createArticle(List<AritcleForm> dtos) {

    }
}
