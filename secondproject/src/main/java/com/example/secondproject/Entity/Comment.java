package com.example.secondproject.Entity;

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

}
