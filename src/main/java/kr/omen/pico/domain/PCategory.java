package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="p_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_category_idx")
    private Long pCategoryIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_idx")
    @JsonManagedReference
    private Category category;

    private String kind;

    public PCategory(Photographer photographer,Category category,String kind){
        this.photographer=photographer;
        this.category=category;
        this.kind=kind;
    }
}
