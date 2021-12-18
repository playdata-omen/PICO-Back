package kr.omen.pico.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="work_idx")
    private Long workIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photographer_idx")
    private Photographer photographer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_idx")
    private Category category;

    @Column(length = 100 , nullable = false)
    private String title;

    @Column(length = 100 , nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp created;

    @Column(length = 100)
    private String thumbnail;

    @Builder
    public Work(Photographer photographer, Category category, String title, String content, Timestamp created, String thumbnail) {
        this.photographer=photographer;
        this.category=category;
        this.title=title;
        this.content=content;
        this.created=created;
        this.thumbnail=thumbnail;
    }

    public Work updateThumbnail(String thumbnail){
        this.thumbnail=thumbnail;
        return this;
    }
}
