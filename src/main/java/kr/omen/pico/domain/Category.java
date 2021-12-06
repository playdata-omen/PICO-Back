package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_idx")
    private Long categoryIdx;

    @Column(length = 100)
    private String kind;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    List<Estimate> estimateList = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    List<Work> workList = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    List<PCategory> pCategoryList = new ArrayList<>();
}
