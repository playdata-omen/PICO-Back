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
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="apply_idx")
    private Long applyIdx;

    @ManyToOne
    @JoinColumn(name="estimate_idx")
    @JsonManagedReference
    private Estimate estimate;

    @ManyToOne
    @JoinColumn(name="photographer_idx")
    @JsonManagedReference
    private Photographer photographer;

    @Column(columnDefinition = "varchar(255) default '1'")
    private String status;

    @CreationTimestamp
    private Timestamp created;
}
