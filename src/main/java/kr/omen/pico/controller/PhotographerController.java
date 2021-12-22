package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.PhotographerDTO;
import kr.omen.pico.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PhotographerController {

    @Autowired
    private PhotographerService photographerService;

    @PostMapping("/photographer/register")
    public PhotographerDTO.PhotographerInfo photographerRegister(@RequestBody PhotographerDTO.PhotographerRegister data) {
        return photographerService.photographerRegister(data);
    }
  
    @GetMapping("/user/{userIdx}/photographer")
    public PhotographerDTO.PhotographerInfo getPhotographerInfo(@PathVariable Long userIdx){
        return photographerService.getPhotographerInfo(userIdx);
    }

    // 카테고리로 작가 리스트 검색
    public void searchByCategoryPhotographer(@PathVariable Long categoryIdx) {
        photographerService.searchByCategoryPhotographer(categoryIdx);

    }
    // 작가 이름으로 작가 검색
}
