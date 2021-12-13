package kr.omen.pico.controller;

import javassist.NotFoundException;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import kr.omen.pico.model.ChatMessage;
import kr.omen.pico.dao.chatdao.ChatMessageRepository;
import kr.omen.pico.dao.chatdao.ChatRoomRepository;
import kr.omen.pico.service.ChatMessageService;
import kr.omen.pico.service.ChatRoomService;
import kr.omen.pico.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatService;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageService chatMessageService;
    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */

    @MessageMapping("/chat/message")
//    @GetMapping("/chat/message")
    public void message(ChatMessage message, @Header("token") String token) {

        //Username으로 토큰이 담긴 이름으로 변형해서 sender에 저장
        String sender = jwtTokenProvider.getUserNameFromJwt(token);

        // 로그인 회원 정보로 대화명 설정
        message.setSender(sender);
        // 채팅방 인원수 세팅
        message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));

        chatMessageRepository.save(message);
        // Websocket에 발행된 메시지를 redis로 발행(publish)
        System.out.println("token:" + token);
        chatService.sendChatMessage(message);
    }

    @DeleteMapping("/chatmessage/delete")
    @ResponseBody
    public boolean deleteChatMessage(ChatMessageDTO.Delete dto) {
        boolean result = false;
        try {
            chatMessageService.deleteChatMessage(dto);
            result = true;
        } catch (NotFoundException e) {
//            e.printStackTrace();
        }
        return result;
    }
}
