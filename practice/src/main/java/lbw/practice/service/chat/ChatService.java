package lbw.practice.service.chat;

import lbw.practice.dto.chat.ChatDto;
import lbw.practice.repository.chat.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {
    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final WebClient webClient;
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8000")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public ChatDto sendMessage(ChatDto request) {
        try {
            logger.info("Sending message to FastAPI: {}", request.getMessage());

            // FastAPI 서버로 요청 전송
            Mono<Map> responseMono = webClient.post()
                    .uri("/chat/")  // FastAPI의 엔드포인트와 일치하도록 수정
                    .bodyValue(Map.of("message", request.getMessage()))  // FastAPI의 ChatInput 모델과 일치하도록 수정
                    .retrieve()
                    .bodyToMono(Map.class);

            Map response = responseMono.block();

            if (response == null) {
                logger.error("Received null response from FastAPI");
                throw new RuntimeException("FastAPI 서버로부터 응답을 받지 못했습니다.");
            }

            logger.info("Received response from FastAPI: {}", response);

            // 응답 저장
            ChatDto chat = new ChatDto();
            chat.setMessage(request.getMessage());
            chat.setResponse((String) response.get("response"));
            chat.setCreatedAt(LocalDateTime.now());

            chatRepository.insertChat(chat);

            return chat;
        } catch (Exception e) {
            logger.error("Error while communicating with FastAPI: {}", e.getMessage());
            throw new RuntimeException("FastAPI 서버와 통신 중 오류가 발생했습니다.", e);
        }
    }

    public List<ChatDto> findAll() {
        return chatRepository.findAll();
    }
}