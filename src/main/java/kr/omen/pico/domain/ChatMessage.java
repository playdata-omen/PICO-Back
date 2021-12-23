package kr.omen.pico.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
