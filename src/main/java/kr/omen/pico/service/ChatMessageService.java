package kr.omen.pico.service;

import kr.omen.pico.config.jwt.TokenProvider;
import kr.omen.pico.dao.*;
import kr.omen.pico.domain.*;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
    private final PhotographerRepository photographerRepository;
    private final TokenProvider tokenProvider;


    @Transactional
    public ChatMessage sendMessage(Long roomIdx,String message,String token) {
        Authentication Test = tokenProvider.getAuthentication(token);
        ChatMessage chatMessage = null;
        ChatRoom chatRoom = chatRoomRepository.findById(roomIdx).get();
        Photographer photographer = photographerRepository.findById(chatRoom.getPhotographer().getPhotographerIdx()).get();
        System.out.println(Test.getName().getClass().getName());
        Long idx = Long.parseLong(Test.getName());
        User user = userRepository.findById(idx).get();
        chatMessage = chatMessageRepository.save(ChatMessage.builder()
                .chatRoom(chatRoom)
                .user(user)
                .message(message)
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
