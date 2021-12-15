package kr.omen.pico.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_idx")
    private Long photoIdx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="work_idx")
    private Work work;

    @Column(length = 100)
    private String title;

    @Column(name="stored_file_path")
    private String storedFilePath;

    @Column(name="file_size")
    private String fileSize;

    @Builder
    public Photo(Work work, String title, String storedFilePath, String fileSize) {
        this.work=work;
        this.title=title;
        this.storedFilePath=storedFilePath;
        this.fileSize=fileSize;
    }
}
