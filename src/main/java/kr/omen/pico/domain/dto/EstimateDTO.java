package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.User;
import lombok.Data;

import java.time.LocalDate;

public class EstimateDTO {

    @Data
    public static class Create{
        private long user;
        private long category;
        private String content;
        private String city;
        private String address;
        private LocalDate startDate;
        private LocalDate endDate;
        private String status;

        public Estimate toEntity(User user,Category category){
            return Estimate.builder()
                    .user(user)
                    .category(category)
                    .content(content)
                    .city(city)
                    .address(address)
                    .startDate(startDate)
                    .endDate(endDate)
                    .status(status)
                    .build();
        }

    }

}
