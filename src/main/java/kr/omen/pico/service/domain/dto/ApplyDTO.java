package kr.omen.pico.service.domain.dto;

import kr.omen.pico.service.domain.Apply;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

public class ApplyDTO {

    @Getter
    @RequiredArgsConstructor
    public static class Get{
        private Long applyIdx;
        private Long photographerIdx;
        private Integer status;
        private Timestamp created;
        private Boolean isApplied;
        private EstimateDTO.SimpleEstimate estimate;

        public Get(Apply entity){
            this.applyIdx=entity.getApplyIdx();
            this.photographerIdx=entity.getPhotographer().getPhotographerIdx();
            this.status=entity.getStatus();
            this.created=entity.getCreated();
            this.isApplied=entity.getIsApplied();
            this.estimate=new EstimateDTO.SimpleEstimate(entity.getEstimate());
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Card{
        private Long applyIdx;
        private Long estimateIdx;
        private Long photographerIdx;
        private Integer status;
        private Timestamp created;

        public Card(Apply entity){
            this.applyIdx=entity.getApplyIdx();
            this.estimateIdx=entity.getEstimate().getEstimateIdx();
            this.photographerIdx=entity.getPhotographer().getPhotographerIdx();
            this.status=entity.getStatus();
            this.created=entity.getCreated();
        }
    }
}
