package kr.omen.pico.domain;

import kr.omen.pico.domain.dto.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter()
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
    private boolean isRegister;

    @Column(name="is_photographer")
    private boolean isPhotographer;

    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private Photographer photographer;

//    @OneToMany(mappedBy = "user")
//    @JsonBackReference
//    private List<Estimate> estimateList = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    @JsonBackReference
//    private List<Review> reviewList = new ArrayList<>();
}
