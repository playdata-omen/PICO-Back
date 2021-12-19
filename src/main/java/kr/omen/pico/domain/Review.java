package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_idx")
    private Long reviewIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @CreationTimestamp
    private Timestamp created;

    @Column(length = 100)
    private String content;

    @Column
    private Float grade;

    @Builder
    public Review(User user, Photographer photographer,Timestamp created, String content, Float grade) {
        this.user=user;
        this.photographer=photographer;
        this.created=created;
        this.content=content;
        this.grade=grade;
    }
}
