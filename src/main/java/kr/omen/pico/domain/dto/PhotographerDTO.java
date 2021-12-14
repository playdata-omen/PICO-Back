package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import lombok.*;

@Data
@NoArgsConstructor
public class PhotographerDTO {

    private long user;
    private boolean isStudio;
    private float grade;
    private String activityCity;
    private String activityAddress;
    private String studioCity;
    private String studioAddress;
    private boolean otherArea;

    @Getter
    @Setter
    public static class PhotographerRegister {
        long userIdx;
        long photographerIdx;
        Boolean isStudio;
        String activityCity;
        String activityAddress;
        String studioCity;
        String studioAddress;
        Boolean otherArea;

        public Photographer toEntity(User user) {
            return Photographer.builder()
                    .user(user)
                    .photographerIdx(photographerIdx)
                    .isStudio(isStudio)
                    .activityCity(activityCity)
                    .activityAddress(activityAddress)
                    .studioCity(studioCity)
                    .studioAddress(studioAddress)
                    .otherArea(otherArea)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class PhotographerInfo {
        Long photographerIdx;
        Float grade;
        Boolean isStudio;
        String activityCity;
        String activityAddress;
        String studioCity;
        String studioAddress;
        Boolean otherArea;
    }
}
