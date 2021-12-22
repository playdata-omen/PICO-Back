package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.User;
import kr.omen.pico.domain.ChatMessage;
import kr.omen.pico.domain.ChatRoom;
import lombok.Data;

import java.sql.Timestamp;

public class ChatMessageDTO {
    @Data
    public static class Delete {
        private Long chatMessageIdx;
    }

    @Data
    public static class Create {
        private Long chatRoomIdx;
        private Long userIdx;
        private String message;
        private Timestamp created;

        public ChatMessage toEntity(ChatRoom chatRoom, User user){
            return ChatMessage.builder()
                    .user(user)
                    .chatRoom(chatRoom)
                    .message(message)
                    .created(created)
                    .build();
        }

    }
}
