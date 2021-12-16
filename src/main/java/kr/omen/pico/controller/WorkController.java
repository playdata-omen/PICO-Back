package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.WorkDTO;
import kr.omen.pico.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping("work/add")
    public ResponseDTO.WorkResponse insertWork(@RequestBody WorkDTO.Create dto){
        ResponseDTO.WorkResponse response = workService.insertWork(dto);
        return response;
    }

}
