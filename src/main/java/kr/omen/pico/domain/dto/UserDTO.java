package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.User;
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
    private String role;
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
                .role(role)
                .isRegister(isRegister)
                .isPhotographer(isPhotographer)
                .build();

    }
}
