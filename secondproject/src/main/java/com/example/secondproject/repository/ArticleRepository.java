package com.example.secondproject.repository;

import com.example.secondproject.Entity.Article;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //jap 나 CRUD 등을 상속 받고, Entity 클래스와 해당 클래스의 가장 큰 데이터 타입을 <클래스명, 데이터타입> 형식으로 입력한다.

    @Override
    ArrayList<Article> findAll();

}
