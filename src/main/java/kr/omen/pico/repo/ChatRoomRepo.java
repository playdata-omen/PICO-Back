package kr.omen.pico.repo;

import kr.omen.pico.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {
}
