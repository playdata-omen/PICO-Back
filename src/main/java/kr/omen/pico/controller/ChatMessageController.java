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
    public ResponseDTO.ChatMessageListResponse findAllMessage(@PathVariable Long chatroomidx){
        List<ChatMessageDTO.Card> chatMessageList = chatMessageService.findMessageListByChatRoom(chatroomidx);
        return new ResponseDTO.ChatMessageListResponse(chatMessageList);
    }

    @MessageMapping("/sendTo/{roomIdx}/{message}/{token}")
    public void sendMessage(@DestinationVariable("roomIdx") Long roomIdx,
                     @DestinationVariable("message") String message,
                     @DestinationVariable("token") String token) throws Exception{

        ChatMessage chatMessage = chatMessageService.sendMessage(roomIdx,message,token);
        ResponseDTO.chatMessageResponse dto = new ResponseDTO.chatMessageResponse(chatMessage);
        simpMessagingTemplate.convertAndSend("/topics/sendTo/"+roomIdx,dto.toString());
    }

    @MessageMapping("/Template")
    public void SendTemplateMessage() {
        simpMessagingTemplate.convertAndSend("/topics/sendTo/4", "Template222");
    }
}
