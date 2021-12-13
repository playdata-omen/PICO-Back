package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.User;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

public class EstimateDTO {

    @Data
    public static class Create{
        private long idx;
        private long user;
        private long category;
        private Timestamp created;
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
                    .created(created)
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
