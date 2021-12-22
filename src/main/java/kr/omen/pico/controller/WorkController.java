package kr.omen.pico.controller;

import kr.omen.pico.service.domain.dto.WorkDTO;
import kr.omen.pico.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping("/work")
    public Map<String, Object> addData(@RequestBody WorkDTO.Create data) throws IOException {
        return workService.uploadWork(data);
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
