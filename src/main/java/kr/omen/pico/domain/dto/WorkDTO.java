package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Work;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

public class WorkDTO {

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Create{

        private Long photographerIdx;
        private Long categoryIdx;
        private String title;
        private String content;
        private Timestamp created;
        private String thumbnail;

        public Create(Work entity,Long photographerIdx,Long categoryIdx){
            this.photographerIdx=photographerIdx;
            this.categoryIdx=categoryIdx;
            this.title=entity.getTitle();
            this.content=entity.getContent();
            this.created=entity.getCreated();
            this.thumbnail=entity.getThumbnail();
        }

        public Work toEntity(Photographer photographer, Category category){
            return Work.builder()
                    .photographer(photographer)
                    .category(category)
                    .title(title)
                    .content(content)
                    .created(created)
                    .thumbnail(thumbnail)
                    .build();
        }
    }
}
