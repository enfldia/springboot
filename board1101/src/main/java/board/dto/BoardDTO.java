package board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
//DTO(data transfer Object)
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits; //조회수
    private LocalDateTime boardCreateTime; //게시글 생성 시간
    private LocalDateTime boardUpdateTime; //게시글 수정시 수정 시간

}
