package kr.omen.pico.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserDTO {

    @Getter
    public static class Login {
        String code;
        String provider;
    }
    @Builder
    @ToString
    public static class Register {
        String id;
        String provider;
        String providerId;
        String nickName;
        String name;
        String email;
        String phone;
        boolean isPhotographer;
    }

}
