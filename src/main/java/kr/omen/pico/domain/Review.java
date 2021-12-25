package kr.omen.pico.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photographer_idx")
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

    public Review update(String content, Float grade){
        this.content = content;
        this.grade = grade;
        return this;
    }
}
