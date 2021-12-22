package kr.omen.pico.service.domain;

import kr.omen.pico.service.domain.dto.Role;
import kr.omen.pico.service.domain.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_idx")
    private Long userIdx;

    @Column(name = "user_id", length = 100 , nullable = false)
    private String userId;

    @Column(length = 100 , nullable = false)
    private String provider;

    @Column(length = 100 , nullable = false)
    private String providerId;

    @Column(length = 100)
    private String nickName;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 100 , nullable = false)
    private Role role;

    @Column(name="is_register")
    private Boolean isRegister;

    @Column(name="is_photographer")
    private Boolean isPhotographer;

    @Builder
    public User(String userId, String provider, String providerId, String nickName, String name, String email, String phone, Role role, Boolean isRegister, Boolean isPhotographer) {
        this.userId = userId;
        this.provider = provider;
        this.providerId = providerId;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.isRegister = isRegister;
        this.isPhotographer = isPhotographer;
    }

//    public User update(String email, String name, String nickName, String phone, Boolean isPhotographer) {
    public User update(UserDTO.UserInfo data) {
        this.email = data.getEmail();
        this.name = data.getName();
        this.nickName = data.getNickName();
        this.phone = data.getPhone();
        this.isPhotographer = data.getIsPhotographer();
        this.isRegister = true;

        return this;

    }
}
