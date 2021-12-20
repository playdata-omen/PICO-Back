package kr.omen.pico.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_idx")
    private Long categoryIdx;

    @Column(length = 100)
    private String kind;

    @Builder
    public Category(String kind){
        this.kind=kind;
    }
}
