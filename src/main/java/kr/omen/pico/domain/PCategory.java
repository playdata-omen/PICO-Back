package kr.omen.pico.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name="p_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_category_idx")
    private Long pCategoryIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photographer_idx")
    private Photographer photographer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_idx")
    private Category category;

    private String kind;

    @Builder
    public PCategory(Photographer photographer, Category category, String kind) {
        this.photographer = photographer;
        this.category = category;
        this.kind = kind;
    }
}
