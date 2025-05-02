package lbw.practice.service;

import lbw.practice.dto.BoardDto;
import lbw.practice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void saveBoard(BoardDto boardDto) {
        boardRepository.saveBoard(boardDto);
    }

   public List<BoardDto> getAllBoards() {
        return boardRepository.getAllBoards();
   }

}
