package com.example.firstproject.controller;

import com.example.firstproject.DTO.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;
    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String ceateArticle(ArticleForm form){
        System.out.println(form.toString());
        //1. DTO를 Entity 변환
        Article article = form.toEntity();
        System.out.println(article.toString());
        //2. Repository 에게 Entity를 DB로 저장하게 함
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
