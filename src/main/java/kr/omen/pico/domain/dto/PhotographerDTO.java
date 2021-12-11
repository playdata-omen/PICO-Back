package kr.omen.pico.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhotographerDTO {

    private long user;
    private boolean isStudio;
    private float grade;
    private String city;
    private String address;
    private String studioAddress;
    private boolean otherArea;

}
