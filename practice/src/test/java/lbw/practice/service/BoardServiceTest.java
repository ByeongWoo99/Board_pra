// src/test/java/lbw/practice/service/BoardServiceTest.java
package lbw.practice.service;

import lbw.practice.dto.BoardDto;
import lbw.practice.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional //실행이 되고 난 후 자동 rollback
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    private BoardDto boardDto1;
    private BoardDto boardDto2;

    @BeforeEach
    void setUp() {
        boardDto1 = new BoardDto();
        boardDto1.setTitle("제목 테스트1");
        boardDto1.setContent("내용 테스트1");

        boardDto2 = new BoardDto();
        boardDto2.setTitle("제목 테스트2");
        boardDto2.setContent("내용 테스트2");
    }

    @DisplayName("게시글 저장 확인")
    @Test
    void saveBoard() {

//        //given @BeforeEach 이용해 중복제거
//        boardDto1 = new BoardDto();
//        boardDto1.setTitle("제목 테스트");
//        boardDto1.setContent("내용 테스트");

        //when
        boardService.saveBoard(boardDto1);

        //then
        assertNotNull(boardDto1);
        System.out.println(boardDto1.getBno());
        assertEquals("제목 테스트1", boardDto1.getTitle());
        assertEquals("내용 테스트1", boardDto1.getContent());
    }

    @DisplayName("게시글 목록 확인")
    @Test
    void getAllBoards() {

//        //given, 게시글 저장 ,  @BeforeEach 이용해 중복제거
//        BoardDto boardDto1 = new BoardDto();
//        boardDto1.setTitle("게시글 제목 테스트1");
//        boardDto1.setContent("게시글 내용 테스트1");

        boardService.saveBoard(boardDto1);

//        BoardDto boardDto2 = new BoardDto();
//        boardDto2.setTitle("게시글 제목 테스트2");
//        boardDto2.setContent("게시글 내용 테스트2");

        boardService.saveBoard(boardDto2);

        //when
        List<BoardDto> boardList = boardService.getAllBoards();

        // then
        assertNotNull(boardList);
        assertEquals(2, boardList.size());

        assertEquals("제목 테스트1", boardList.get(0).getTitle());
        assertEquals("내용 테스트1", boardList.get(0).getContent());

        assertEquals("제목 테스트2", boardList.get(1).getTitle());
        assertEquals("내용 테스트2", boardList.get(1).getContent());

    }

}
