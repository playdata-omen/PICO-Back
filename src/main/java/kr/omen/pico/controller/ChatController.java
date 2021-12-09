package kr.omen.pico.controller;

import kr.omen.pico.model.ChatMessage;
import kr.omen.pico.repo.ChatMessageRepository;
import kr.omen.pico.repo.ChatRoomRepository;
import kr.omen.pico.service.ChatService;
import kr.omen.pico.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatService chatService;
    private final ChatMessageRepository chatMessageRepository;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
//    @GetMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {
        String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        message.setSender(nickname);
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));

        chatMessageRepository.save(message);
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        System.out.println("token:" + token);
        chatService.sendChatMessage(message);
    }
}
