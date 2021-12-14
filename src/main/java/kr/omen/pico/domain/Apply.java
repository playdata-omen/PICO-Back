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
//@AllArgsConstructor
//@RequiredArgsConstructor
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_idx")
    private Long applyIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="estimate_idx")
    @JsonManagedReference
    private Estimate estimate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @Column(columnDefinition = "varchar(255) default '1'")
    private String status;

    @CreationTimestamp
    private Timestamp created;

    private Boolean isApplied;

    @Builder
    public Apply(Estimate estimate, Photographer photographer,String status,Timestamp created){
        this.estimate=estimate;
        this.photographer=photographer;
        this.status=status;
        this.created=created;
    }
}
