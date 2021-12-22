package kr.omen.pico.dao;

import kr.omen.pico.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
//    ChatRoom findChatRoomByRoomId(String roomId);

}
