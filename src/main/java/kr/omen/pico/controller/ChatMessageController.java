package kr.omen.pico.controller;

import kr.omen.pico.domain.ChatMessage;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {


    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatMessageService chatMessageService;

    @GetMapping("/chatmessage/{chatroomidx}")
    public List<ChatMessageDTO.Card> findAllMessage(@PathVariable Long chatroomidx){
        List<ChatMessageDTO.Card> chatMessageList = chatMessageService.findMessageListByChatRoom(chatroomidx);
        return chatMessageList;
    }

    @MessageMapping("/sendTo/{roomIdx}/{message}/{token}")
    public void sendMessage(@DestinationVariable("roomIdx") Long roomIdx,
                     @DestinationVariable("message") String message,
                     @DestinationVariable("token") String token) throws Exception{

        ChatMessageDTO.Card messageCard = chatMessageService.sendMessage(roomIdx,message,token);
        simpMessagingTemplate.convertAndSend("/topics/sendTo/1",messageCard);
    }

    @MessageMapping("/Template")
    public void SendTemplateMessage() {
        simpMessagingTemplate.convertAndSend("/topics/sendTo/4", "Template222");
    }
}
