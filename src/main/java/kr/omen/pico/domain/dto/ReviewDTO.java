package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
