package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.omen.pico.domain.dto.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_idx")
    private Long userIdx;

    @Column(length = 100 , nullable = false)
    private String id; // 2026857599@kakao.social

    @Column(length = 100 , nullable = false)
    private String provider; // kakao

    @Column(length = 100 , nullable = false)
    private String providerId; // 2026857599

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
    private boolean isRegister;

    @Column(name="is_photographer")
    private boolean isPhotographer;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    List<Estimate> estimateList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    List<Photographer> photographerList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    List<Review> reviewList = new ArrayList<>();

}
