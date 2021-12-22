package kr.omen.pico.service.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_idx")
    private Long applyIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="estimate_idx")
    private Estimate estimate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photographer_idx")
    private Photographer photographer;

    private Integer status;

    @CreationTimestamp
    private Timestamp created;

    private Boolean isApplied;

    @Builder
    public Apply(Estimate estimate, Photographer photographer, Integer status, Timestamp created, Boolean isApplied) {
        this.estimate = estimate;
        this.photographer = photographer;
        this.status = status;
        this.created = created;
        this.isApplied = isApplied;
    }

    public Apply update(Integer status) {
        this.status = status;
        return this;
    }

    public Apply updateApplied(Boolean isApplied){
        this.isApplied=isApplied;
        return this;
    }
}
