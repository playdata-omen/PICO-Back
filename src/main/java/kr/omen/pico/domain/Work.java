package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="work_idx")
    private Long workIdx;

    @ManyToOne
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @ManyToOne
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

    @OneToMany(mappedBy = "work")
    @JsonBackReference
    List<Photo> photoList = new ArrayList<>();


}
