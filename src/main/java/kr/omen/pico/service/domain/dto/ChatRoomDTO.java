package kr.omen.pico.service.domain.dto;

import kr.omen.pico.service.domain.Photographer;
import kr.omen.pico.service.domain.User;
import kr.omen.pico.service.domain.ChatRoom;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRoomDTO {

    @Data
    public static class Delete{
        private Long ChatRoomIdx;
    }

    @Data
    public static class Create{
        private Long userIdx;
        private Long photographerIdx;

        public ChatRoom toEntity(User user, Photographer photographer){
            return ChatRoom.builder()
                    .user(user)
                    .photographer(photographer)
                    .build();
        }
    }
}
