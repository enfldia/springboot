package com.example.firstproject.api;


import com.example.firstproject.DTO.CommentDto;
import com.example.firstproject.service.CommentService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    public final CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto){
        //@RequestBody CommentDto dto = Spring이 Jason 데이터를  CommentDto 개체로 변환하여
        //컨트롤러메서드에 전달. 이후 컨트롤러 메서드에서 dto를 사용할 수 있다.

        //서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createDto);

    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        //서비스에 위임
        CommentDto updateDto = commentService.update(id,dto);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 댓글 삭제

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        //서비스에 위임
        CommentDto deleteDto = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }

}