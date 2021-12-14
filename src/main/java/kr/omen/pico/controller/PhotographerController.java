package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.PhotographerDTO;
import kr.omen.pico.service.PhotographerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotographerController {

    @Autowired
    private PhotographerService photographerService;

    @GetMapping("photographer/getInfo/{uidx}")
    public PhotographerDTO getPhotographerInfo(@PathVariable Long uidx){
        return photographerService.getPhotographerInfo(uidx);
    }
}
