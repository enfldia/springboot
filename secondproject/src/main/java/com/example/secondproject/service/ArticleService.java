package com.example.secondproject.service;

import com.example.secondproject.Entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String findById(Long id){
        return articleRepository.findById(id);
    }


}
