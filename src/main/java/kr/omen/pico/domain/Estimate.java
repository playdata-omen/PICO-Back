package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="estimate_idx")
    private Long estimateIdx;

    @ManyToOne
    @JoinColumn(name="user_idx")
    @JsonManagedReference
    private User user;

    @ManyToOne
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

    @OneToMany(mappedBy = "estimate")
    @JsonBackReference
    List<Apply> applyList = new ArrayList<>();

}
