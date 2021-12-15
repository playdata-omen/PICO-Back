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
  
    @GetMapping("photographer/getInfo/{userIdx}")
    public PhotographerDTO.PhotographerInfo getPhotographerInfo(@PathVariable Long userIdx){
        return photographerService.getPhotographerInfo(userIdx);
    }
}
