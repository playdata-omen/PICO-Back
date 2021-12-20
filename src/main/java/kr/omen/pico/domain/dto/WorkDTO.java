package kr.omen.pico.domain.dto;

import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Work;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

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
        private List<String> images;

        public Create(Work entity,Long photographerIdx,Long categoryIdx,List<String> images){
            this.photographerIdx=photographerIdx;
            this.categoryIdx=categoryIdx;
            this.title=entity.getTitle();
            this.content=entity.getContent();
            this.created=entity.getCreated();
            this.thumbnail=entity.getThumbnail();
            this.images=images;
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

    @Getter
    @RequiredArgsConstructor
    public static class GetWork{
        private Long workIdx;
        private Long photographerIdx;
        private String title;
        private String content;
        private Long categoryIdx;
        List<String> photos;

        public GetWork(Work entity,List<String> photos){
            this.workIdx=entity.getWorkIdx();
            this.photographerIdx=entity.getPhotographer().getPhotographerIdx();
            this.title=entity.getTitle();
            this.content=entity.getContent();
            this.categoryIdx=entity.getCategory().getCategoryIdx();
            this.photos=photos;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class WorkCard{
        Long workIdx;
        String thumbnail;
        String title;

        public WorkCard(Work entity){
            this.workIdx=entity.getWorkIdx();
            this.thumbnail=entity.getThumbnail();
            this.title=entity.getTitle();
        }
    }
}
