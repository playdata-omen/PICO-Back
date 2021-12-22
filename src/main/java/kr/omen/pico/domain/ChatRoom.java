package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.omen.pico.domain.Photo;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

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

    @Builder
    public ChatRoom(User user, Photographer photographer){
        this.user = user;
        this.photographer = photographer;
    }

}
