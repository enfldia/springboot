package com.example.secondproject.api;

import com.example.secondproject.DTO.CommentDto;
import com.example.secondproject.Entity.Comment;
import com.example.secondproject.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
//final 필드들을 모아서 생성자를 자동으로 생성,
// 해당 클래스가 의존하는 객체를 생성자 매개변수를 통해 주입 받아 사용할 수 있다.

public class CommnetApiController {
    public final CommentService commentService;

    //댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    //ResponseEntity는 HttpEntity를 상속받고 있는 API의 응답 헤더와 HttpStatus를 직접 제어할 수 있도록 도와주는 클래스
    public ResponseEntity<List<CommentDto>> comment(@PathVariable Long articleId){
        //서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        //
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
}
