package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class ResponseDTO {

    /**
     * Estimate 관련 ResponseDTO
     */
    @Data
    public static class EstimateResponse{
        private Long id;
        private Long user;
        private Long category;
        private Timestamp created;
        private String content;
        private String city;
        private String address;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;

        public EstimateResponse(Estimate entity){
            this.id=entity.getEstimateIdx();
            this.user=entity.getUser().getUserIdx();
            this.category=entity.getCategory().getCategoryIdx();
            this.created=entity.getCreated();
            this.content=entity.getContent();
            this.city=entity.getCity();
            this.address=entity.getAddress();
            this.startDate=entity.getStartDate();
            this.endDate=entity.getEndDate();
            this.status=entity.getStatus();
        }
    }

    //유저가 견적서 클릭 시 보여줄 상세페이지와 지원한 작가목록 DTO
    @Data
    public static class DetailResponse{
        private Long id;
        private Long user;
        private Long category;
        private Timestamp created;
        private String content;
        private String city;
        private String address;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;
        private List<SimplePhotographerCard> applyList;

        public DetailResponse(Estimate entity,List<SimplePhotographerCard> applies){
            this.id=entity.getEstimateIdx();
            this.user=entity.getUser().getUserIdx();
            this.category=entity.getCategory().getCategoryIdx();
            this.created=entity.getCreated();
            this.content=entity.getContent();
            this.city=entity.getCity();
            this.address=entity.getAddress();
            this.startDate=entity.getStartDate();
            this.endDate=entity.getEndDate();
            this.status=entity.getStatus();
            this.applyList=applies;
        }
    }

    //목록 출력 시 최소한의 정보만을 뿌려줄 DTO
    @Data
    public static class SimpleCard{
        private Long category;
        private String cname;
        private Timestamp created;

        public SimpleCard(Estimate entity){
            this.category=entity.getCategory().getCategoryIdx();
            this.cname=entity.getCategory().getKind();
            this.created=entity.getCreated();
        }
    }

    /**
     * Photographer 관련 ResponseDTO
     */

    //해당하는 견적서에 지원한 작가들 목록 출력시 최소한의 정보만 뿌려줄 DTO
    @Data
    public static class SimplePhotographerCard{
        //작가 idx/ 지원 idx가 들어가야함.
        private Long puidx;
        private Long aidx;
        private String name;
        private List<Long> pCategoryList;

        public SimplePhotographerCard(Photographer entity, Apply apply,List<Long> pCategories){
            this.puidx=entity.getUser().getUserIdx();
            this.aidx=apply.getApplyIdx();
            this.name=entity.getUser().getName();
            this.pCategoryList=pCategories;
        }
    }
}
