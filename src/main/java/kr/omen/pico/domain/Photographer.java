package kr.omen.pico.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Photographer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photographer_idx")
    private Long photographerIdx;

    @OneToOne
    @JoinColumn(name="user_idx")
    private User user;

    @Column(name="is_studio")
    private Boolean hasStudio;

    @Column
    private Float grade;

    @Column(length = 100)
    private String activityCity;

    @Column(length = 100)
    private String activityAddress;
    
    // 변수명 변경 가능성 있음
    @Column(length= 100)
    private String studioCity;

    @Column(length= 100)
    private String studioAddress;

    // 활동영역 타지역 협의가능 여부
    @Column(length = 100)
    private Boolean isOtherArea;

    @Builder
    public Photographer(Long photographerIdx, User user, Boolean hasStudio, Float grade, String activityCity, String activityAddress, String studioCity, String studioAddress, Boolean isOtherArea) {
        this.photographerIdx = photographerIdx;
        this.user = user;
        this.hasStudio = hasStudio;
        this.grade = grade;
        this.activityCity = activityCity;
        this.activityAddress = activityAddress;
        this.studioCity = studioCity;
        this.studioAddress = studioAddress;
        this.isOtherArea = isOtherArea;
    }

    @OneToMany(mappedBy = "photographer", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Review> reviewList = new ArrayList<>();
}
