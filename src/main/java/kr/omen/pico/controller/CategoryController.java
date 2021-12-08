package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.CategoryDTO;
import kr.omen.pico.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/getAllCategory")
    public List<CategoryDTO> getAllCategory(){
        List<CategoryDTO> list = categoryService.getAllCategory();
        return list;
    }

}
