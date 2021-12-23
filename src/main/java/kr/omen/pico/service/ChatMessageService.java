package kr.omen.pico.service;

import kr.omen.pico.dao.*;
import kr.omen.pico.domain.*;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ApplyRepository applyRepository;
    private final EstimateRepository estimateRepository;
    private final UserService userService;

    @Transactional
    public ChatMessage sendMessage(ChatMessageDTO.Create dto) {
        ChatMessage chatMessage = null;
        Apply apply = applyRepository.findById(dto.getApplyIdx()).get();

        ChatRoom chatRoom = chatRoomRepository.findById(dto.getChatRoomIdx()).get();
        Estimate estimate = estimateRepository.findById(apply.getEstimate().getEstimateIdx()).get();
        User user = userService.getUser();
        System.out.println("chatRoom: " + chatRoom.getChatRoomIdx());
        System.out.println("estimate: " + estimate.getEstimateIdx());
        System.out.println("user: " + user.getUserIdx());
        chatMessage = chatMessageRepository.save(ChatMessage.builder()
                                                            .chatRoom(chatRoom)
                                                            .user(user)
                                                            .message(dto.getMessage())
                                                            .created(dto.getCreated())
                                                            .build());
        return chatMessage;
    }

    public List<ChatMessageDTO.Card> findMessageListByChatRoom(Long chatroomidx) {
        List<ChatMessage> chatList = null;
        ChatRoom chatroom = chatRoomRepository.findById(chatroomidx).get();
        chatList= chatMessageRepository.findAllByChatRoom(chatroom);

//        User user = chatroom.getUser();
        List<ChatMessageDTO.Card> testMessage = new ArrayList<>();
        for(ChatMessage chatMessage : chatList){
            User user = userRepository.findById(chatMessage.getUser().getUserIdx()).get();
            testMessage.add(new ChatMessageDTO.Card(chatMessage, user));
        }
        return testMessage;
    }
}
