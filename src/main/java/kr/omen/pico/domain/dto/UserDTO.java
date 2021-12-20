package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.User;
import lombok.*;

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
                    .isRegister(false)
                    .isPhotographer(false)
                    .build();
        }
    }

    @Builder
    @Getter
    @Setter
    public static class LoginInfo {
        Long userIdx;
        String name;
        String email;
        String phone;
        String nickName;
        Boolean isRegister;
        Boolean isPhotographer;
        String accessToken;
        String refreshToken;
    }

    @Getter
    public static class UserInfo {
        Long userIdx;
        String name;
        String email;
        String phone;
        String nickName;
        Boolean isPhotographer;

        public User toEntity() {
            return User.builder()
                    .name(name)
                    .email(email)
                    .phone(phone)
                    .nickName(nickName)
                    .isPhotographer(isPhotographer)
                    .isRegister(true)
                    .build();
        }
    }


    @Getter
    @RequiredArgsConstructor
    public static class SimpleUser{
        private Long userIdx;
        private String name;

        public SimpleUser(User entity){
            userIdx=entity.getUserIdx();
            name=entity.getName();
        }
    }

    @Data
    public static class Review {
        private Long userIdx;
        private String name;

        public Review(User entity){
            userIdx= entity.getUserIdx();
            name= entity.getName();
        }
    }
}
