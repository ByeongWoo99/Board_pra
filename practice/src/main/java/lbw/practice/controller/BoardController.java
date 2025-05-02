package lbw.practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import lbw.practice.dto.BoardDto;
import lbw.practice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {


    private final BoardService boardService;

    @GetMapping
    public String getAllBoards(Model model) {
        List<BoardDto> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    @PostMapping
    public String saveBoard(BoardDto boardDto, Model model) {
        boardService.saveBoard(boardDto);
        model.addAttribute("board", boardDto);
        return "redirect:/boards";
    }
}
