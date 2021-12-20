package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.WorkDTO;
import kr.omen.pico.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping("/work")
    public void addData(@RequestBody WorkDTO.Create data) throws IOException {
        workService.uploadWork(data);
    }

    @GetMapping("/work/{workIdx}")
    public WorkDTO.GetWork getWorkDetail(@PathVariable Long workIdx){
        WorkDTO.GetWork work = workService.getWorkDetail(workIdx);
        return work;
    }

    @GetMapping("/user/{userIdx}/work")
    public List<WorkDTO.WorkCard> getWorkList(@PathVariable Long userIdx){
        List<WorkDTO.WorkCard> list = workService.getWorkList(userIdx);
        return list;
    }
}
