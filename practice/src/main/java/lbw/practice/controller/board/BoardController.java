package lbw.practice.controller.board;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import lbw.practice.dto.board.BoardDto;
import lbw.practice.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    //전체 게시글 조회
    @GetMapping
    public String getAllBoards(Model model) {
        List<BoardDto> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board/boardList";
    }

    //게시글 저장
    @PostMapping
    public String saveBoard(BoardDto boardDto, Model model) {
        boardService.saveBoard(boardDto);
        model.addAttribute("board", boardDto);
        return "redirect:/boards";
    }

    //게시글 작성 페이지 이동
    @GetMapping("/new")
    public String newBoard() {
        return "board/boardForm";
    }
}
