package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional  // 예외 포함 x
public class ArticleService {
    private final ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    // 단건 조회
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        } // 기존 데이터 수정 방지
        return articleRepository.save(article);
    }


    public Article update(Long id, ArticleForm dto) {
        // 1. dto -> Entiry
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2.  target 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4. 업데이트
        // article: 폼에서 작성한 데이터(수정)
        // target: 원본 데이터(DB에서 꺼내온 데이터)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) {
        // 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }
        // 데이터 삭제 및 반환
        articleRepository.delete(target);
        return target;
    }


    public List<Article> createArticle(List<ArticleForm> dtos) {
//        List<Article> articleList = new ArrayList<>();
//        for (int i = 0; i < dtos.size(); i++) {
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }

        // 1. dtos 리스트에서 스트림생성 - 스트림을 사용하여 데이터를 순회하고 변환
        List<Article> articleList = dtos.stream()
                // 2. map 연산을 사용하여 dto.ToEntity()를 호출하여 ArticleForm 객체를 Article Entity로 변환 -> Article 객체를 생성
                .map(dto -> dto.toEntity())
                // 3. articleList에 변환된 Article 객체를 담아서 최종 결과를 생성
                .collect(Collectors.toList());

//        for (int i = 0; i < articleList.size(); i++) {
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }

        // 1. articleList.stream() 스트림으로 생성
        articleList.stream()
                // 2. articleList에서 article을 꺼내와서 articleRepository에 저장
                .forEach(article -> articleRepository.save(article));


        // 강제 예외를 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패 !!")
        );


        return articleList;
    }
}
