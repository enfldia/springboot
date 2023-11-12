package com.example.secondproject.Entity;

import com.example.secondproject.DTO.CommentDto;
import lombok.*;

import javax.persistence.*;

//Entity = 정보가 저장될 수 있는 사람,장소,물건,사건, 개념등의 명사에 해당한다.
//       = 저장되기 위한 어떤 것(thing)이다.
//       = 데이터베이스 내에서 변별 가능한 객체
@Entity //선언된 클래스를 DB 테이블과 매핑한다.JPA를 사용하므로 JPA가 해당 클래스를 관리한다.
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id //특정 속성을 기본키로 설정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 DB에 위임
    private Long id;

    @ManyToOne //다수(현재 클래스) 대 일(아래에 적히는 클래스) 관계를 알려준다.
    @JoinColumn(name = "article_id")
    //아래 적히는 객체와 현재 테이블의 foreign key를 연결하는데, article의 pk값으로 한다.
    // article 의 pk값을 Comment의 foreign key로 하는데, 그 column의 이름은 article_id 이다.
    private Article article;

    @Column //필드의 속성을 지정할떄 사용, 객체 필드를 테이블 칼럼과 맵핑
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        //Article 타입의 article 과 CommetDto 타입의 dto를 매개로 받는 Comment 타입의 createComment 란 명의 정적 메소드 생성

        //예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글에 id가 없어야 합니다.");
        //매개변수 dto의 id가 null이 아니라면 런타임 에러 발생시키며 ("") 안에 있는 값을 리턴;
        //런타임 에러는 메소드나 클래스에 적합하지 않는 인자값을 넘겨주었을때 뜨는 에러.

        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못 되었습니다.");
        //매개변수 dto의 ArticleId 와 매개변수 article의 id가 갖지 않을 경우 런타임 에러 발생

        //엔티티 생성 및 반환
        return new Comment( // 새로운 변수에 값을 답지 않고 바로 Comment 엔티티 값을 리턴.
                dto.getId(),
                article,
                dto.getNickName(),
                dto.getBody()
        );
    }
}
