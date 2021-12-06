package kr.omen.pico.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_idx")
    private Long photoIdx;

    @ManyToOne
    @JoinColumn(name="work_idx")
    @JsonManagedReference
    private Work work;

    @Column(length = 100)
    private String title;

    @Column(name="stored_file_path")
    private String storedFilePath;

    @Column(name="file_size")
    private String fileSize;
}
