package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.User;
import lombok.Builder;
import lombok.Getter;

public class UserDTO {
    @Getter
    public static class Login {
        String code;
        String provider;
    }
    @Builder
    @Getter
    public static class Register {
        String userId;
        String provider;
        String providerId;
        String name;
        String email;
        String phone;

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .providerId(providerId)
                    .provider(provider)
                    .name(name)
                    .email(email)
                    .phone(phone)
                    .role(Role.ROLE_USER)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class Info {
        String userId;
        String name;
        String email;
        String phone;
        String nickName;
        boolean isRegister;
        boolean isPhotographer;
    }


}
