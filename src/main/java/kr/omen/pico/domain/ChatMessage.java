package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChatMessage {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="chat_message_idx")
    private Long chatMessageIdx;

    @ManyToOne
    @JoinColumn(name="chat_room_idx")
    @JsonIgnore
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name="user_idx")
    @JsonIgnore
    private User user;

    private String message; // 메시지

    @CreationTimestamp
    private Timestamp created;

    @Builder
    public ChatMessage(ChatRoom chatRoom, User user, String message, Timestamp created) {
        this.chatRoom = chatRoom;
        this.user = user;
        this.message = message;
        this.created=created;
    }

}
