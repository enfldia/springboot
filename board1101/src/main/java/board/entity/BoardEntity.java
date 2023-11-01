package board.entity;

import board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table") //jpa를 사용하며 이 어노테이션을 사용하면 DB에 자동으로 테이블이 생성되는데.아래에 정의한 명으로 생성된다.
public class BoardEntity extends BaseEntity{
    @Id //pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jpa의  auto_increment
    private Long id;

    @Column(length = 20,nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column // 디폴트 값은 크기 255 ,null 가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter((boardDTO.getBoardWriter()));
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
