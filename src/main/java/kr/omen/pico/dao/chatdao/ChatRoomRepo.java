package kr.omen.pico.dao.chatdao;

import kr.omen.pico.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {
//    ChatRoom findChatRoomByRoomId(String roomId);

}
