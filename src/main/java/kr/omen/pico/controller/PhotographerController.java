package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.PhotographerDTO;
import kr.omen.pico.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class PhotographerController {

    @Autowired
    private PhotographerService photographerService;

    @PostMapping("/photographer/register")
    public PhotographerDTO.PhotographerInfo photographerRegister(@RequestBody PhotographerDTO.PhotographerRegister data) {
        return photographerService.photographerRegister(data);
    }
  
    @GetMapping("photographer/getInfo/{uidx}")
    public PhotographerDTO getPhotographerInfo(@PathVariable Long uidx){
        return photographerService.getPhotographerInfo(uidx);
    }
}
