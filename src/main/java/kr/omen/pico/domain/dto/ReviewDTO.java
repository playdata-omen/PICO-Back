package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ReviewDTO {

    @Data
    public static class Create {
        private Long applyIdx;
        private Long photographerIdx;
        private Timestamp created;
        private String content;
        private float grade;

        public Review toEntity(User user, Photographer photographer){
            return Review.builder()
                    .user(user)
                    .photographer(photographer)
                    .created(created)
                    .content(content)
                    .grade(grade)
                    .build();
        }
    }

    @Data
    public static class Delete {
        private Long userIdx;
        private Long photographerIdx;
    }

    @Data
    public static class Update {
        private Long reviewIdx;
        private Long photographerIdx;
//        private Long userIdx;
        private String content;
        private Float grade;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Card {
        Long reviewIdx;
        Timestamp created;
        String content;
        Float grade;
        Long photographerIdx;
        UserDTO.SimpleUser user;

        public Card(Review entity,User user) {
            this.reviewIdx = entity.getReviewIdx();
            this.created=entity.getCreated();
            this.content=entity.getContent();
            this.grade=entity.getGrade();
            this.photographerIdx=entity.getPhotographer().getPhotographerIdx();
            this.user = new UserDTO.SimpleUser(user);

        }
    }
}
