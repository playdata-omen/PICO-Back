package kr.omen.pico.dao;

import kr.omen.pico.service.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
