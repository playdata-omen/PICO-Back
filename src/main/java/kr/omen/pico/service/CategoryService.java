package kr.omen.pico.service;

import kr.omen.pico.dao.CategoryRepository;
import kr.omen.pico.service.domain.Category;
import kr.omen.pico.service.domain.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategory(){
        List<Category> list = categoryRepository.findAll();

        List<CategoryDTO> dtolist = new ArrayList<>();

        for(Category category : list){
            dtolist.add(new CategoryDTO(category));
        }

        return dtolist;
    }
}
