package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.ChatMessage;
import kr.omen.pico.domain.ChatRoom;
import kr.omen.pico.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ChatMessageDTO {
    @Data
    public static class Delete {
        private Long chatMessageIdx;
    }

    @Data
    @RequiredArgsConstructor
    public static class Create {
        private Long applyIdx;
        private Long chatRoomIdx;
//        private Long userIdx;
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

    @Getter
    @RequiredArgsConstructor
    public static class Card {
//        private Long userIdx;
        private Long chatMessageIdx;
        private String message;
        private Timestamp created;
        private UserDTO.Info user;

        public Card(ChatMessage entity, User user){
            chatMessageIdx = entity.getChatMessageIdx();
            this.message = entity.getMessage();
            this.created = entity.getCreated();
            this.user = new UserDTO.Info(user);
        }
    }
}
