package lbw.practice.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDto {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private int viewCnt;
    private int commentCnt;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private int deleteFlag;
}
