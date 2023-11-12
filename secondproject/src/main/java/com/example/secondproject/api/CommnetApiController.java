package com.example.secondproject.api;

import com.example.secondproject.DTO.CommentDto;
import com.example.secondproject.Entity.Comment;
import com.example.secondproject.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        //CommentDto의 타입의 List를 갖는 ResponseEntity를 comment라는 메소드 생성.articleId를 매개변수로 갖는다.
        //서비스에 위임

        List<CommentDto> dtos = commentService.comments(articleId);
        //매개변수 articleId 을 commentService.comments 메소드 실행 ->
        //매개 변수와 같은 값을 갖는게 있는지 commentRepository.findByArticleId 메소드 사용해 찾고,List<comment> 형태로 저장.->
        //저장된 값은 엔티티 타입이므로 Dto 형태로 변환 시켜기 위해 List<CommentDto> 형태의 ArrayList 선언 ->
        //for문의 size()를 이용해서 엔티티 형태의 List<Comment>를 List<CommentDto> 형태로 변환하고 add()를 사용해서 List에 삽->
        //retrun된 값을 List<CommnetDto> dtos에 대입.

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
        //응답 받은 갑싱 정상 작동으로 하면 바디에 dtos의 값을 출력.
    }

    //댓글 생성
    @PostMapping("/apo/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, //맵핑되어 들어오는 주소값에서 특정 값을 매개값으로 받아오는 어노테이션.데이터 타입과 명으로 작성
                                             @RequestBody CommentDto dto){ //HTTP 요청의 본문(body)에 포함된 데이터를 읽어와 Java 객체로 맵핑
        //@RequestBody CommnetDto dto = Spring이 JSON 데이터를 CommentDto 객체로 변환하여
        //컨트롤러 메서드에 전달, 이후 컨트롤러 메서드에서 dto를 사용할 수 있다.

        //서비스에 위임
        CommentDto createDto = commentService.create(articleId,dto);
        //articleId,dto를 서비스의 create로 넘겨서 예외 처리-> Comment에서 엔티티화 ->
        // commentRepository에서 save 후 -> 그 값을 CommnetDto 의 commentDtoCreate로 dto형태로 변환 ;

        return ResponseEntity.status(HttpStatus.OK).body(createDto);
        //body에 createDto를 대입 하려는데.http가 되돌려주는 값이 정상이라면 createDto를 출력
    }
}
