package com.example.secondproject.service;

import com.example.secondproject.DTO.CommentDto;
import com.example.secondproject.Entity.Article;
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
        //매개변수를 articleId로 갖는 CommentDto를 List 형태의 comments라는 메소드 생성.

        //댓글 목록 조회
        List<Comment> comments = commentRepositroy.findByArticleId(articleId);
        //매개변수를 이용해서 commentRepository.findByArticleId 로 DB에서 같은 articleId를 사용하는 댓글을 찾음

        //변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        //엔티티 형태의 comments 리스트들을 dto 형태로 바꿔 담기 위한 CommentDto 형태의 dtos라는 새로운 ArryList 선언;

        for(int i=0; i<comments.size();i++){
            Comment comment = comments.get(i); //i번째 comments 를 comment에 대입
            CommentDto dto = CommentDto.createCommentDto(comment); // comment를 createCommentDto로 dto 형태로 변환후 dto란 변수로 저장
            dtos.add(dto); //dto를 dtos 라는 arryList에 삽입
        }//comments 리스트 수만 큼 반복

        return dtos;
    }

    @Transactional //DB에 접근하므로 트랜잭션 어노테이션으로 문제가 발생하면 롤백되도록 해야함
    public CommentDto create(Long articleId, CommentDto dto) {

        //게시글 조회 및 예외 발생

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패"));
        //Atricle은 optional 클래스라 예외처리 방식을 적어야한다.
        //값이 null이면  ()-> new IllegalArgumentException 으로 전달하여 런타임 에러를 발생시켜 "댓글 생성 실패"를 출력
        //런타임 에러 = 메소드 또는 생성자의 규칙에 맞지 않는 인자를 사용할 경우 발생.

        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        //매개변수 dto와 article의 값으로 Comment의 createCommet 메소드를 실행하고 그 리턴 받은 값을 Comment의 타입의 comment에 대입

        //댓글 엔티티를 DB에 저장
        Comment created = commentRepositroy.save(comment);
        //위에서 대입 받은 comment를 commentRePository의 save 메소드를 사용하여 DB에 저장하고 그 값을 created에 대입

        //Dto로 변경하여 반환
        return CommentDto.createCommentDto(created);
        //엔티티 형태로 저장한 값인 created를 사용을 하기 위해서 다시 Dto 형태로 변환하고 값을 리턴

    }
}
