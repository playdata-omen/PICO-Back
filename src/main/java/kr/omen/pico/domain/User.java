package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_idx")
    private Long userIdx;

    @Column(length = 100 , nullable = false)
    private String id;

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

    @Column(length = 100 , nullable = false)
    private String role;

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
