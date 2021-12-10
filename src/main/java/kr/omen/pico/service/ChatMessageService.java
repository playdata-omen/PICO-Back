package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import kr.omen.pico.model.ChatMessage;
import kr.omen.pico.repo.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public void deleteChatMessage(ChatMessageDTO.Delete dto) throws NotFoundException{
        ChatMessage chatMessage = findOne(dto.getChatMessageIdx());
        chatMessageRepository.deleteById(chatMessage.getIdx());
    }

    public ChatMessage findOne(Long chatMessageIdx) throws NotFoundException{
        ChatMessage chatMessage = null;
        try {
            chatMessage = chatMessageRepository.findById(chatMessageIdx).orElseThrow(()->new Exception.NotFoundException("ChatRoom with idx: " + chatMessageIdx + " is not valid"));
        } catch (Exception.NotFoundException e) {
//            e.printStackTrace();
        }
        return chatMessage;
    }
}
