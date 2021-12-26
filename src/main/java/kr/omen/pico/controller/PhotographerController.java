package kr.omen.pico.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.omen.pico.domain.dto.PhotographerDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.service.PhotographerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PhotographerController {

    private final PhotographerService photographerService;

    @PostMapping("/photographer/register")
    public PhotographerDTO.PhotographerInfo photographerRegister(@RequestBody PhotographerDTO.PhotographerRegister data) {
        return photographerService.photographerRegister(data);
    }
  
    @GetMapping("/user/{userIdx}/photographer")
    public PhotographerDTO.PhotographerInfo getPhotographerInfo(@PathVariable Long userIdx){
        return photographerService.getPhotographerInfo(userIdx);
    }

    // 카테고리로 작가 리스트 검색
    @GetMapping("/photographer/category/{categoryIdx}")
    public List<ResponseDTO.searchPhotographerCard> searchByCategoryPhotographer(@PathVariable Long categoryIdx) {
        return photographerService.searchByCategoryPhotographer(categoryIdx);
    }
    // 작가 이름으로 작가 검색
    @GetMapping("/photographer/search")
    public List<ResponseDTO.searchPhotographerCard> searchByNamePhotographer(@RequestParam String keyword) {
        return photographerService.searchByNamePhotographer(keyword);
    }
}
