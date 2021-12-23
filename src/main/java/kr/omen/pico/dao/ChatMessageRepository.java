package kr.omen.pico.dao;

import kr.omen.pico.domain.ChatMessage;
import kr.omen.pico.domain.ChatRoom;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChatRoom(ChatRoom chatroom);
}
