package kr.omen.pico.domain.dto;

import lombok.Getter;

public class UserDTO {

    @Getter
    public static class Login {
        String code;
        String provider;
    }
    public static class Register {
        String id;
        String nickName;
        String name;
        String email;
        String phone;
        boolean isRegister;
        boolean isPhotographer;
    }

}
