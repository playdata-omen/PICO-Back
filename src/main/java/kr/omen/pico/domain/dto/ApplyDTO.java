package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Apply;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

public class ApplyDTO {

    @Getter
    @RequiredArgsConstructor
    public static class Get{
        private Long applyIdx;
        private Long photographerIdx;
        private String status;
        private Timestamp created;
        private EstimateDTO.SimpleEstimate estimate;

        public Get(Apply entity){
            this.applyIdx=entity.getApplyIdx();
            this.photographerIdx=entity.getPhotographer().getPhotographerIdx();
            this.status=entity.getStatus();
            this.created=entity.getCreated();
            this.estimate=new EstimateDTO.SimpleEstimate(entity.getEstimate());
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Card{
        private Long applyIdx;
        private Long estimateIdx;
        private Long photographerIdx;
        private String status;
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
