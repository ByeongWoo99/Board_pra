package lbw.practice.repository.chat;

import lbw.practice.dto.chat.ChatDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatRepository {
    void insertChat(ChatDto chat);
    List<ChatDto> findAll();
}
