package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PhotographerDTO {

    @Getter
    @Setter
    public static class PhotographerRegister {
        Long userIdx;
        Long photographerIdx;
        Boolean hasStudio;
        String activityCity;
        String activityAddress;
        String studioCity;
        String studioAddress;
        Boolean isOtherArea;
        List<Long> category;

        public Photographer toEntity(User user) {
            return Photographer.builder()
                    .user(user)
                    .photographerIdx(photographerIdx)
                    .hasStudio(hasStudio)
                    .activityCity(activityCity)
                    .activityAddress(activityAddress)
                    .studioCity(studioCity)
                    .studioAddress(studioAddress)
                    .isOtherArea(isOtherArea)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class PhotographerInfo {
        Long photographerIdx;
        Float grade;
        Boolean hasStudio;
        String activityCity;
        String activityAddress;
        String studioCity;
        String studioAddress;
        Boolean isOtherArea;
        List<Long> category;
    }

    @Getter
    @RequiredArgsConstructor
    public static class SimplePhotographer{
        private Long photographerIdx;
        private String name;
        private Float grade;

        public SimplePhotographer(Photographer entity){
            this.photographerIdx=entity.getPhotographerIdx();
            this.name=entity.getUser().getName();
            this.grade=entity.getGrade();
        }
    }
}
