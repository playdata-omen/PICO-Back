package kr.omen.pico.domain;

import kr.omen.pico.domain.dto.Role;
import lombok.*;

import javax.persistence.*;

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

//    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<Estimate> estimateList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<Photographer> photographerList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<Review> reviewList = new ArrayList<>();

//    @Builder
//    public User(String id,String provider,String providerId,String nickName,String name,String email,String phone,
//                String role,boolean isRegister,boolean isPhotographer){
//        this.id=id;
//        this.provider=provider;
//        this.providerId=providerId;
//        this.nickName=nickName;
//        this.name=name;
//        this.email=email;
//        this.phone=phone;
//        this.role=role;
//        this.isRegister=isRegister;
//        this.isPhotographer=isPhotographer;
//    }

}
