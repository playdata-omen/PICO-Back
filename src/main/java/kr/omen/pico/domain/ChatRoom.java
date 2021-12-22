package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ChatRoom {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="chat_room_idx")
    private Long chatRoomIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photographer_idx")
    @JsonIgnore
    private Photographer photographer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estimate_idx")
    @JsonIgnore
    private Estimate estimate;

    @Builder
    public ChatRoom(User user, Photographer photographer, Estimate estimate){
        this.user = user;
        this.photographer = photographer;
        this.estimate = estimate;
    }

}
