package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Estimate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstimateDTO {

    private long user;
    private long category;
    private String content;
    private String city;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public EstimateDTO(Estimate entity){
        this.user=entity.getUser().getUserIdx();
        this.category=entity.getCategory().getCategoryIdx();
        this.content=entity.getContent();
        this.city=entity.getCity();
        this.address=entity.getAddress();
        this.startDate=entity.getStartDate();
        this.endDate=entity.getEndDate();
        this.status=entity.getStatus();
    }
}
