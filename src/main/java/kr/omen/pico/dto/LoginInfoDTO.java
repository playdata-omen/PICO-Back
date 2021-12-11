package kr.omen.pico.dto;

import lombok.Data;

public class LoginInfoDTO {

    @Data
    public static class Create{
        private String name;
        private String token;
    }
}
