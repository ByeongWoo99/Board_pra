package lbw.practice.config;

import lbw.practice.service.board.BoardService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BoardTestConfig {
    @Bean
    public BoardService boardService() {
        return Mockito.mock(BoardService.class);
    }
}
