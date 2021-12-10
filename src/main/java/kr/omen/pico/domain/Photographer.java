package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photographer_idx")
    private Long photographerIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

    @Column(name="is_studio")//ERD 에는 studio로 되어있지만 bool 타입은 is_로 통일하기로 합의.
    private boolean isStudio;

    @Column
    private float grade;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String address;

    @Column(length= 100)
    private String studioAddress;

    @Column(length = 100)
    private boolean otherArea;

//    @OneToMany(mappedBy = "photographer" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<Apply> applyList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "photographer" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<Work> workList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "photographer" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<Review> reviewList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "photographer" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<PCategory> pCategoryList = new ArrayList<>();

    @Builder
    public Photographer(User user, boolean isStudio, float grade, String city, String address, String studioAddress, boolean otherArea){
        this.user=user;
        this.isStudio=isStudio;
        this.grade=grade;
        this.city=city;
        this.address=address;
        this.studioAddress=studioAddress;
        this.otherArea=otherArea;
    }
}
