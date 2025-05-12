package lbw.practice.repository.board;

import lbw.practice.dto.board.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    //게시글 저장
    void saveBoard(BoardDto boardDto);

    //게시글 목록
    List<BoardDto> getAllBoards();

    //bno 이용해서 특정 게시글 조회
    BoardDto getBoardByBno(int bno);

    //게시글 수정
    void editBoard(BoardDto boardDto);

    //게시글 삭제 플래그 활성화
    void deleteBoard(BoardDto boardDto);
}
