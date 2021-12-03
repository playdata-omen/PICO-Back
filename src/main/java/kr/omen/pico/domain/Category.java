package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @Column(length = 100)
    private String kind;
}
