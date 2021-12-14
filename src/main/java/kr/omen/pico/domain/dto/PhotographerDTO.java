package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Photographer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PhotographerDTO {

    private Long idx;
    private Long user;
    private Boolean isStudio;
    private Float grade;
    private String city;
    private String address;
    private String studioAddress;
    private Boolean otherArea;

    public PhotographerDTO(Photographer entity){
        this.idx=entity.getPhotographerIdx();
        this.user=entity.getUser().getUserIdx();
        this.isStudio=entity.isStudio();
        this.grade=entity.getGrade();
        this.city=entity.getCity();
        this.address=entity.getAddress();
        this.studioAddress=entity.getStudioAddress();
        this.otherArea=entity.isOtherArea();
    }

}
