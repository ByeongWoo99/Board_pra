package lbw.practice.service.board;

import lbw.practice.dto.board.BoardDto;
import lbw.practice.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    //삭제 플래그 활성화 변수
    int activeDeleteFlag = 1;

    //게시글 저장
    public BoardDto saveBoard(BoardDto boardDto) {
        //생성시간 설정 추가
        boardDto.setCreatedAt(LocalDateTime.now());

       boardRepository.saveBoard(boardDto);
       return boardDto;
    }

    //모든 게시글 목록 확인,  회원가입 기능 추가 후 각각의 id 별로 출력되도록 수정
   public List<BoardDto> getAllBoards() {
        return boardRepository.getAllBoards();
   }

   //bno 이용해서 게시글 찾기
   public BoardDto getBoardByBno(int bno) {
        return boardRepository.getBoardByBno(bno);
   }

   //게시글 수정
   public BoardDto editBoard(BoardDto boardDto) {
        boardRepository.editBoard(boardDto);
        return boardDto;
   }

   //게시글 삭제 시, 삭제플래그 활성화
   public void deleteBoard(BoardDto boardDto) {
        boardDto.setDeleteFlag(activeDeleteFlag);
        boardRepository.deleteBoard(boardDto);
   }

}
