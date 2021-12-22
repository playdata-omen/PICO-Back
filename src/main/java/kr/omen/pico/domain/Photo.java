package kr.omen.pico.domain;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Getter
@Entity
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="photo_idx")
    private Long photoIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="work_idx")
    private Work work;

    @Column(length = 100)
    private String title;

    @Column(name="stored_file_path")
    private String storedFilePath;

    @Column(name="file_size")
    private String fileSize;

    @Type(type="json")
    @Column(name="label", columnDefinition = "json")
    private String label;

    @Builder
    public Photo(Work work, String title, String storedFilePath, String fileSize, String label) {
        this.work=work;
        this.title=title;
        this.storedFilePath=storedFilePath;
        this.fileSize=fileSize;
        this.label=label;
    }
}
