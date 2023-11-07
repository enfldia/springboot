package com.example.firstproject.service;

import com.example.firstproject.DTO.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;


    @Transactional(readOnly = true)
    public List<CommentDto> comments(Long articleId){
        //조회 댓글 목록
       List<Comment> comments = commentRepository.findByArticleId(articleId);
       //변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0; i<comments.size();i++){
            Comment comment = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(comment);
//            Comment c = comments.get(i);
//            CommentDto dto = new CommentDto(c.getId(),c.getArticle().getId(),c.getNickname(),c.getBody());
            dtos.add(dto);
        }

//        List<CommentDto> dtos = comments.stream()
//                .map(CommentDto::createCommentDto) -->"::" 는 클래스이름::정적메서드이름  구성해서 사용한다.
//                .collect(Collectors.toList());


        //반환
        return dtos;



//        return commentRepository.findByArticleId(articleId)
//                .stream()
//                .map(comment -> CommentDto.createCommentDto(comment))
//                .collect(Collectors.toList());
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회 및 예외 발생

       Article article =  articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));

       //댓글 엔티티 생성
       Comment comment = Comment.createComment(dto, article);

       //댓글 엔티티를 DB에 저장
       Comment created  = commentRepository.save(comment);
       //Dto로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    public CommentDto update(Long id, CommentDto dto) {
        Comment target =  commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패"));
        //댓글 수정
         target.patch(dto);

         //DB로 갱신
        Comment updated = commentRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 수정 실패"));

        //댓글 삭제
        commentRepository.delete(target);

        //삭제된 댓글을 Dto로 변환
        return CommentDto.createCommentDto(target);
    }
}
