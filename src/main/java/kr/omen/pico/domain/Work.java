package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="work_idx")
    private Long workIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_idx")
    @JsonManagedReference
    private Category category;

    @Column(length = 100 , nullable = false)
    private String title;

    @Column(length = 100 , nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp created;

    @Column(length = 100)
    private String thumbnail;

//    @OneToMany(mappedBy = "work" , cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<Photo> photoList = new ArrayList<>();

    @Builder
    public Work(Photographer photographer,Category category, String title, String content, Timestamp created,String thumbnail){
        this.photographer=photographer;
        this.category=category;
        this.title=title;
        this.content=content;
        this.created=created;
        this.thumbnail=thumbnail;
    }

}
