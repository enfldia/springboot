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
    public List<Article> findAll (){
        List<Article> findAll =articleRepository.findAll();
        return findAll;
    }
}
