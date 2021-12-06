package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_idx")
    private Long reviewIdx;

    @ManyToOne
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @CreationTimestamp
    private Timestamp created;

    @Column(length = 100)
    private String content;

    @Column
    private float grade;
}
