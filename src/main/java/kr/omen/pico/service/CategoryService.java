package kr.omen.pico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.omen.pico.dao.CategoryRepository;
import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategory(){
        List<Category> list = categoryRepository.findAll();

        List<CategoryDTO> dtolist = new ArrayList<>();

        for(Category category : list){
            dtolist.add(new CategoryDTO(category));
        }

        return dtolist;
    }
}
