package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Photographer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import kr.omen.pico.domain.User;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PhotographerDTO {

    private Long idx;
    private Long user;
    private Boolean isStudio;
    private Float grade;
    private String activityCity;
    private String activityAddress;
    private String studioCity;
    private String studioAddress;
    private Boolean otherArea;

    public PhotographerDTO(Photographer entity){
        this.idx=entity.getPhotographerIdx();
        this.user=entity.getUser().getUserIdx();
        this.isStudio=entity.isStudio();
        this.grade=entity.getGrade();
        this.activityCity=entity.getActivityCity();
        this.activityAddress=entity.getActivityAddress();
        this.studioCity=entity.getStudioCity();
        this.studioAddress=entity.getStudioAddress();
        this.otherArea=entity.isOtherArea();
    }

    @Getter
    @Setter
    public static class PhotographerRegister {
        Long userIdx;
        Long photographerIdx;
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
