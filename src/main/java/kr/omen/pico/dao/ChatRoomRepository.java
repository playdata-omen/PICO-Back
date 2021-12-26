package kr.omen.pico.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.omen.pico.domain.ChatRoom;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    ChatRoom findByEstimateAndUserAndPhotographer(Estimate estimate, User user, Photographer photographer);
}
