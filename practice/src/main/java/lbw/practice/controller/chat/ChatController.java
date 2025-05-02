package lbw.practice.controller.chat;

import lbw.practice.dto.chat.ChatDto;
import lbw.practice.service.chat.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/form")
    public String chatForm() {
        return "chat/chatForm";
    }

    @PostMapping
    @ResponseBody
    public ChatDto sendMessage(@RequestBody ChatDto request) {
        return chatService.sendMessage(request);
    }

    @GetMapping
    @ResponseBody
    public List<ChatDto> getAllChats() {
        return chatService.findAll();
    }
}