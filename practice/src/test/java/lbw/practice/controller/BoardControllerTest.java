package lbw.practice.controller;

import lbw.practice.config.BoardTestConfig;
import lbw.practice.dto.BoardDto;
import lbw.practice.service.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BoardController.class)
@Import(BoardTestConfig.class) // 외부 config를 명시
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardService boardService;

    @Test
    void 게시글_저장_요청_테스트() throws Exception {

        //PostMapping 호출 시, saveBoard가 호출되서 작동하지 못 하게 하기 위해서
        doNothing().when(boardService).saveBoard(Mockito.any());

        mockMvc.perform(post("/boards")
                        .param("title", "제목 테스트")
                        .param("content", "내용 테스트"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/boards"));
    }

    @Test
    void 게시글_목록_조회_요청_테스트() throws Exception {
        BoardDto boardDto1 = new BoardDto();
        boardDto1.setTitle("제목 테스트1");
        boardDto1.setContent("내용 테스트1");

        BoardDto boardDto2 = new BoardDto();
        boardDto2.setTitle("제목 테스트2");
        boardDto2.setContent("내용 테스트2");

        //List.of 이용해 불변 List 생성
        List<BoardDto> dummyList = List.of(
                boardDto1,
                boardDto2
        );

        Mockito.when(boardService.getAllBoards()).thenReturn(dummyList);

        mockMvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boards"))
                .andExpect(view().name("boardList"));
    }
}