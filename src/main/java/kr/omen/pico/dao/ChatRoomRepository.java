package kr.omen.pico.dao;

import kr.omen.pico.domain.ChatRoom;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
//    ChatRoom findChatRoomByRoomId(String roomId);

    ChatRoom findByEstimateAndUserAndPhotographer(Estimate estimate, User user, Photographer photographer);
}
