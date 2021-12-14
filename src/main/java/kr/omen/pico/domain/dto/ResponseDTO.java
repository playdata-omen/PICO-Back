package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class ResponseDTO {

    /**
     * Estimate 관련 ResponseDTO
     */
    @Data
    public static class EstimateResponse{
        private Long id;
        private User user;
        private Category category;
        private String content;
        private String city;
        private String address;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;

        public EstimateResponse(Estimate entity){
            this.id=entity.getEstimateIdx();
            this.user=entity.getUser();
            this.category=entity.getCategory();
            this.content=entity.getContent();
            this.city=entity.getCity();
            this.address=entity.getAddress();
            this.startDate=entity.getStartDate();
            this.endDate=entity.getEndDate();
            this.status=entity.getStatus();
        }
    }

    @Data
    public static class DetailResponse{
        private Long id;
        private User user;
        private Category category;
        private String content;
        private String city;
        private String address;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;
        private List<Apply> applyList;

        public DetailResponse(Estimate entity,List<Apply> applies){
            this.id=entity.getEstimateIdx();
            this.user=entity.getUser();
            this.category=entity.getCategory();
            this.content=entity.getContent();
            this.city=entity.getCity();
            this.address=entity.getAddress();
            this.startDate=entity.getStartDate();
            this.endDate=entity.getEndDate();
            this.status=entity.getStatus();
            this.applyList=applies;
        }
    }
}
