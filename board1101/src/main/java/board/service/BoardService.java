package board.service;

import board.dto.BoardDTO;
import board.entity.BoardEntity;
import board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//DTO -> Entity (Entity class에서 실행)
//Entity -> DTO (DTO class에서 실행)
//Entuty class는 결국 DB와 연관이 있기 때문에 뷰단에 노출을 시키 않는게 좋다.최대한 서비스 클래스까지만 오도록 하는게 좋음
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
}
