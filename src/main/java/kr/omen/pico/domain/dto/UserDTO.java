package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import lombok.Data;

@Data
public class UserDTO {
  
    private Long userIdx;
    private String id;
    private String provider;
    private String providerId;
    private String nickName;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private boolean isRegister;
    private boolean isPhotographer;

    public User toEntity(){
        return User.builder()
                .userIdx(userIdx)
                .id(id)
                .provider(provider)
                .providerId(providerId)
                .nickName(nickName)
                .name(name)
                .email(email)
                .phone(phone)
                .role(Role.ROLE_USER)
                .isRegister(isRegister)
                .isPhotographer(isPhotographer)
                .build();

    }

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
