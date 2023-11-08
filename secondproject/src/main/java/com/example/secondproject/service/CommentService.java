package com.example.secondproject.service;

import com.example.secondproject.DTO.CommentDto;
import com.example.secondproject.Entity.Comment;
import com.example.secondproject.repository.ArticleRepository;
import com.example.secondproject.repository.CommentRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
//예외가 발생하면, Rollback 처리를 해준다.

public class CommentService {
    private final CommentRepositroy commentRepositroy;

    private final ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        //댓글 목록 조회
        List<Comment> comments = commentRepositroy.findByArticleId(articleId);
        //변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0; i<comments.size();i++){
            Comment comment = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(comment);
            dtos.add(dto);
        }
        return dtos;
    }
}
