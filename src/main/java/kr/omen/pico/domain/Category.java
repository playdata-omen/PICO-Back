package kr.omen.pico.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_idx")
    private Long categoryIdx;

    @Column(length = 100)
    private String kind;

//    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<Estimate> estimateList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<Work> workList = new ArrayList<>();
//
//    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private final List<PCategory> pCategoryList = new ArrayList<>();

    @Builder
    public Category(String kind){
        this.kind=kind;
    }
}
