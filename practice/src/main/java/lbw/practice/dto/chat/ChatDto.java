package lbw.practice.dto.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ChatDto {
    private int id;
    private String message;
    private String response;
    private LocalDateTime createdAt;

}
