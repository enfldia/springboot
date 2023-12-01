package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    // findAll을 ArrayList로 사용하려고 Override
    @Override
    ArrayList<Article> findAll();

}
