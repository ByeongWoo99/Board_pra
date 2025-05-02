package lbw.practice.repository;

import lbw.practice.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardRepository {
    void saveBoard(BoardDto boardDto);

    List<BoardDto> getAllBoards();
}
