package kr.omen.pico.domain;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="estimate_idx")
    private Long estimateIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_idx")
    @JsonManagedReference
    private Category category;

    @Column(length = 100)
    private String content;

    @CreationTimestamp
    private Timestamp created;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String address;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    private Integer status;

    @Builder
    public Estimate(User user, Category category, String content, Timestamp created,String city,String address,
                    LocalDate startDate,LocalDate endDate,Integer status){
        this.user=user;
        this.category=category;
        this.content=content;
        this.created=created;
        this.city=city;
        this.address=address;
        this.startDate=startDate;
        this.endDate=endDate;
        this.status=status;
    }

    public Estimate updateStatus(Integer status){
        this.status=status;
        return this;
    }
}
