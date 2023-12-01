package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test // 해당 메서드가 테스트를 위한 코드라는 것을 선언하는 어노테이션
    @Transactional
    void index() { // 성공과 실패의 경우를 한 가지 테스트에서 실행
        // 예상 시나리오
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");

        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));
        // 실제 경과
        List<Article> articles = articleService.index();
        // 비교(예상 시나리오와 실제 결과가 같은지 비교한다.
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    @Transactional
    void show_성공() { // 존재하는 id 입력
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void show_실패() { // 존재할 수 없는 id 입력
        // 예상
        Long id = -1L;
        Article expected = null;
        // 실제
        Article article = articleService.show(id);
        // 비교
        assertEquals(expected, article); // null 값은 toString 메서드 사용x
    }


    @Test
    @Transactional
    void create_성공() { // title과 content만 있는 dto 입력
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article article = articleService.create(dto);
        assertEquals(expected.toString(), article.toString());
        System.out.println("예상 결과: " + expected.toString());
        System.out.println("실제 결과: " + article.toString());

    }

    @Test
    @Transactional
    void create_실패() { // id가 포함된 dto 입력
        // 예상
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공() {
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, "가나가나가나", "12121212");
        Article expected = new Article(id, "가나가나가나", "12121212");
        // 실제
        Article article = articleService.update(id, dto);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_실패() {
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, "가나가나가나", "12121212");
        Article expected = null;
        // 실제
        Article article = articleService.update(-1L, dto);
        // 비교
        assertNull(article);
    }

    @Test
    @Transactional
    void delete_성공() {
        // 예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패() {
        // 예상
        Long id = -1L; // 존재하지 않는 id
        Article expected = null;
        // 실제
        Article article = articleService.delete(id);
        // 비교
        assertNull(article);
    }
}


