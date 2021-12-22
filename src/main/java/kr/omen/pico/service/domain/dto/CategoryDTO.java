package kr.omen.pico.service.domain.dto;

import kr.omen.pico.service.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private long categoryIdx;
    private String kind;

    public CategoryDTO(Category entity){
        this.categoryIdx=entity.getCategoryIdx();
        this.kind = entity.getKind();
    }

}
