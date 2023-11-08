package com.example.secondproject.repository;

import com.example.secondproject.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepositroy  extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM comment WHERE article_id = : articleId",nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    @Query(value = "SELECT * FROM comment WHERE nickname = : nickname",nativeQuery = true)
    List<Comment> findByNickname(String nickname);
}
