package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name="p_category")
@NoArgsConstructor
@AllArgsConstructor
public class PCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_category_idx")
    private Long pCategoryIdx;

    @ManyToOne
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @ManyToOne
    @JoinColumn(name="category_idx")
    @JsonManagedReference
    private Category category;

    private String kind;
}
